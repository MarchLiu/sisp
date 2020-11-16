import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast.{Add, And, Def, Divide, Element, Env, Eq, Expression, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Sub}
import sisp.parsers.Parser

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 18:19
 */
class AndSpec extends AnyFlatSpec with Matchers {
  import jaskell.parsec.Txt._

  val parser = new Parser
  val env = new Env
  env.put("def", new Def)
  env.put("if", new If)

  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)
  env.put("and", new And)
  env.put("true?", new IsTrue)
  env.put("false?", new IsFalse)
  env.put("==", new Eq)
  env.put("!=", new Eq)
  env.put(">", new Great)
  env.put("<", new Less)
  env.put(">=", new GreatOrEquals)
  env.put("<=", new LessOrEquals)

  "XAndY" should "true only (and true true)" in {
    parser ask "(and (> 1 0) (> 2 1))" flatMap { element =>
      element.asInstanceOf[Element].eval(env)
    } should be (Success(true))

    parser ask "(and (> 1 0) (< 2 1))" flatMap { element =>
      element.asInstanceOf[Element].eval(env)
    } should be (Success(false))

    parser ask "(and (> 0 0) (> 2 1))" flatMap { element =>
      element.asInstanceOf[Element].eval(env)
    } should be (Success(false))

    (parser ask "(and (> 0 abc) (> 2 1))" flatMap { element =>
      element.asInstanceOf[Element].eval(env)
    }).isFailure should be (true)

  }
}
