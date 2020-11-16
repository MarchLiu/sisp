import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast._
import sisp.parsers.Parser

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 17:07
 */
class CondSpec extends AnyFlatSpec with Matchers {

  import jaskell.parsec.Txt._

  val parser = new Parser
  val env = new Env
  env.put("def", new Def)
  env.put("cond", new Cond)

  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)
  env.put("true?", new IsTrue)
  env.put("false?", new IsFalse)
  env.put("==", new Eq)
  env.put("!=", new Eq)
  env.put(">", new Great)
  env.put("<", new Less)
  env.put(">=", new GreatOrEquals)
  env.put("<=", new LessOrEquals)

  "Equals" should "check equals" in {
    parser ask "(cond (== 5 5) 1 (== 9 (* 3 3)) 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(1))

    parser ask "(cond (== 5.01 5) 1 (== 9 (* 3 3)) 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(0))

    parser ask "(cond (== 5 5.0000001) 1 (* 3 3))" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(9))
  }

  "Greater" should "true if x > y " in {
    parser ask "(cond (> 5.0001 5) (* 2 3.14) 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(6.28))

    parser ask "(cond (> 5 5) 1 (> 3.14 3) 3.14)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(3.14))

    parser ask "(cond (> 5 5.1) 1 (> 3.14 3.14) 3.14 (* 2 3.14))" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Success(6.28))
  }

}
