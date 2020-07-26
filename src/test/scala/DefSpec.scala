import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.Repl.{env, parser}
import sisp.ast.{Add, Def, Divide, Element, Env, Sub}
import sisp.parsers.Parser

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 16:37
 */
class DefSpec extends AnyFlatSpec with Matchers {
  val env = new Env;
  env.put("def", new Def)
  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)

  "Def" should "def a var and then use" in {
    parse("(def pi 3.14)") should be (Right(3.14))
    parse("(* 2 pi)") should be (Right(6.28))
  }

  def parse(source:String): Either[Exception, Any] = {
    val parser = new Parser

    parser ask source flatMap {
      case element: Element => element.eval(env)
      case result: Either[Exception, Any] => result
      case any => Right(any)
    }
  }
}
