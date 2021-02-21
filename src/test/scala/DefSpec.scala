import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast._
import sisp.parsers.Parser
import org.scalatest.TryValues._
import scala.util.{Success, Try}


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
    parse("(def pi 3.14)").success.value shouldBe 3.14
    parse("(* 2 pi)").success.value shouldBe 6.28
  }

  def parse(source:String): Try[Any] = {
    val parser = new Parser

    parser ask source flatMap {
      case element: Element => element.eval(env)
      case result: Try[Any] => result
      case any => Success(any)
    }
  }
}
