import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast.{Add, And, Def, Divide, Do, Env, Eq, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Sub}
import sisp.parsers.Parser

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 15:25
 */
class DoSpec extends AnyFlatSpec with Matchers {
  import jaskell.parsec.Txt._

  val parser = new Parser
  val env = new Env
  env.put("def", new Def)
  env.put("do", new Do)
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

  "Do" should "run sources in sort" in {
    parser apply "(do (+ 7 5) (- 3 1))" flatMap env.eval should be (Success(2))
  }

  "Do Def" should "run sources with def" in {
    parser apply "(do (def pi 3.14) (+ 7 5) (- pi 1))" flatMap env.eval should be (Success(2.14))
  }

}
