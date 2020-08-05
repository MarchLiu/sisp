import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.Sisp
import sisp.ast.{Expression, NumberElement, Quote}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:51
 */
class QuoteSpec extends AnyFlatSpec with Matchers {
  val sisp = new Sisp
  "Quote" should "quote anything" in {
    sisp.read("'(+ 2 3)") should matchPattern { case _: Right[_, Quote] => }

    sisp.parse("'(+ 2 3)") should matchPattern { case _: Right[_, Expression] => }

    sisp.parse("'(+ 2 3)") flatMap sisp.eval should matchPattern { case _: Right[_, Expression] => }

    sisp.read("'3.14") should matchPattern {case _: Right[_, Quote] =>}

    sisp.parse("'3.14") should matchPattern {case _: Right[_, NumberElement] =>}

    sisp.parse("'3.14") flatMap sisp.eval should be (Right(3.14))

  }
}
