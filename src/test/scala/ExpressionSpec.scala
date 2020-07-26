import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.ast.{Add, Divide, Env, Expression, Name, NumberElement, Sub}
import sisp.parsers.Parser

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 17:17
 */
class ExpressionSpec extends AnyFlatSpec with Matchers {
  val env = new Env;
  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)

  "Number" should "extract number element from number string" in {
    val parser = new Parser
    parser ? "123" match {
      case Right(value) => value.asInstanceOf[NumberElement] should be (NumberElement(123))
      case any => fail(s"expect 123 but get $any")
    }
  }

  "Name" should "parse a name string from source" in {
    val parser = new Parser
    parser ? "name" match {
      case Right(value) => value.asInstanceOf[Name].name should be ("name")
      case any => fail(s"expect [name] but get $any")
    }
  }

  "Basic" should "get result for simple expressions" in {
    val parser = new Parser
    parser ? "(+ 1 2 3)" match {
      case Right(result) => result.asInstanceOf[Expression].eval(env) should be (Right(6))
      case any => fail(s"expect sum 1, 2, 3 but get $any")
    }
    parser ? "(* 1 2 3)" match {
      case Right(result) => result.asInstanceOf[Expression].eval(env) should be (Right(6))
      case any => fail(s"expect product 1, 2, 3 but get $any")
    }
  }

  "Complex" should "get result for simple expressions" in {
    val parser = new Parser
    parser ? "(+ 2 (* 2 3))" match {
      case Right(result) => result.asInstanceOf[Expression].eval(env) should be (Right(8))
      case any => fail(s"expect 8 but get $any")
    }

    parser ? "(/ (* 2 3) 2)" match {
      case Right(result) => result.asInstanceOf[Expression].eval(env) should be (Right(3))
      case any => fail(s"expect 3 but get $any")
    }

  }
}
