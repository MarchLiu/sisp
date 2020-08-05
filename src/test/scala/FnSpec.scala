import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast.{Add, And, Def, Divide, Env, Eq, Fn, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Or, Recur, Sub}
import sisp.parsers.Parser

import scala.util

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 15:57
 */
class FnSpec extends AnyFlatSpec with Matchers {
  import jaskell.parsec.Txt._

  val parser = new Parser
  val env = new Env
  env.put("def", new Def)
  env.put("if", new If)

  env.put("fn", new Fn)
  env.put("recur", new Recur)

  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)
  env.put("true?", new IsTrue)
  env.put("false?", new IsFalse)
  env.put("and", new And)
  env.put("or", new Or)
  env.put("==", new Eq)
  env.put("!=", new Eq)
  env.put(">", new Great)
  env.put("<", new Less)
  env.put(">=", new GreatOrEquals)
  env.put("<=", new LessOrEquals)

  "Fn" should "define a function" in {
    parser ask "(def add (fn (x y) (+ x y)))" flatMap env.eval should
      matchPattern {case Right(_) =>}

    parser ask "(add 3.14 3.14)" flatMap env.eval should be (Right(6.28))

    parser ask "(add 2 3)" flatMap env.eval should be (Right(5))

    parser ask "(add 3 2)" flatMap env.eval should be (Right(5))

  }

  "Recur" should "define a recursive function" in {
    parser ask "(def increment (fn (x) (if (< x 10) (recur (* 2 x)) x)))" flatMap env.eval should
      matchPattern {case Right(_) =>}
    parser ask "(increment 2)" flatMap env.eval should be (Right(16))
  }

  "Static Bind" should "success only run preview spec before " in {
    if(env.get("add").isLeft) {
      parser ask "(def add (fn (x y) (+ x y)))" flatMap env.eval should
        matchPattern { case Right(_) => }
    }

    parser ask "(def step6 (fn (x) (if (< x 10) (recur (add 6 x)) x)))" flatMap env.eval should
      matchPattern {case Right(_) =>}
    parser ask "(step6 2)" flatMap env.eval should be (Right(14))

  }
}
