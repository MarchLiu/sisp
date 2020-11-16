import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.Sisp
import sisp.ast.{Add, And, Def, Divide, Env, Eq, Fn, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Or, Recur, Sub}
import sisp.parsers.Parser

import scala.util
import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 15:57
 */
class FnSpec extends AnyFlatSpec with Matchers {
  val sisp: Sisp = new Sisp

  "Fn" should "define a function" in {
    sisp parse "(def add (fn (x y) (+ x y)))" should
      matchPattern {case Success(_) =>}

    sisp parse "(add 3.14 3.14)" should be (Success(6.28))

    sisp parse "(add 2 3)" should be (Success(5))

    sisp parse "(add 3 2)" should be (Success(5))

  }

  "Recur" should "define a recursive function" in {
    sisp parse "(def increment (fn (x) (if (< x 10) (recur (* 2 x)) x)))" should
      matchPattern {case Success(_) =>}
    sisp parse "(increment 2)" should be (Success(16))
  }

  "Static Bind" should "success only run preview spec before " in {
    if(sisp.get("add").isFailure) {
      sisp parse "(def add (fn (x y) (+ x y)))" should
        matchPattern { case Success(_) => }
    }

    sisp parse "(def step6 (fn (x) (if (< x 10) (recur (add 6 x)) x)))" should
      matchPattern {case Success(_) =>}
    sisp parse "(step6 2)" should be (Success(14))

  }
}
