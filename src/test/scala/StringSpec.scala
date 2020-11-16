import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import sisp.parsers.{Parser, StringParser}

import scala.util.Success

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/24 16:07
 */
class StringSpec extends AnyFlatSpec with Matchers {
  "Basic" should "parse simple string" in {
    val parser = new StringParser
    parser ? "\"I'm a string\"" should be (Success("I'm a string"))
  }

  "Escape" should "parse a simple string with escape chars" in {
    val parser = new StringParser
    parser ? "\"This is some text what is \\\"String Content\\\"\"" should
      be (Success("This is some text what is \"String Content\""))
  }

  "MixIn" should "parse a simple string by a Parser object" in {
    val parser = new Parser
    parser ? "\"This is some text what is \\\"String Content\\\"\"" should
      be (Success("This is some text what is \"String Content\""))
  }
}
