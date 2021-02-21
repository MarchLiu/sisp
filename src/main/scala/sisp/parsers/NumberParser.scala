package sisp.parsers

import jaskell.parsec.{Decimal, Parsec, State}
import jaskell.parsec.Txt.decimal
import sisp.ast.NumberElement

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/19 23:21
 */
class NumberParser extends Parsec[Char, Any] {
  val parser: Decimal = decimal
  override def apply(s: State[Char]): Try[Any] = {
    parser ? s map {result => NumberElement(result.toDouble)}
  }
}
