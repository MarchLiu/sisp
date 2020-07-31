import jaskell.parsec.State
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.Repl.env
import sisp.ast.{Add, Def, Divide, Env, Eq, Expression, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Sub}
import sisp.parsers.Parser

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 17:07
 */
class IfSpec extends AnyFlatSpec with Matchers {
  import jaskell.parsec.Txt._

  val parser = new Parser
  val env = new Env
  env.put("def", new Def)
  env.put("if", new If)

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
    parser ask "(if (== 5 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (== 5 5.0000001) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))
  }

  "Greater" should "true if x > y " in {
    parser ask "(if (> 5.0001 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (> 5 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))

    parser ask "(if (> 5 5.1) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))
  }

  "Less" should "true if x < y " in {
    parser ask "(if (< -5 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (< 5 0.5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))

    parser ask "(if (< 5.1 5.1) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))
  }

  "GreatOrEquals" should "true if x >= y " in {
    parser ask "(if (>= 5.0001 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (>= 5 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (>= 5 5.1) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))
  }

  "LessOrEquals" should "true if x <= y " in {
    parser ask "(if (<= -5 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (<= -0.5 -0.5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(1))

    parser ask "(if (< 5.1 5) 1 0)" flatMap { expr =>
      expr.asInstanceOf[Expression].eval(env)
    } should be(Right(0))
  }

}
