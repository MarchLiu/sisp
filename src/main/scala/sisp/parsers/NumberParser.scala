package sisp.parsers

import jaskell.parsec.{Decimal, Parsec, State}
import jaskell.parsec.Txt.decimal
import sisp.ast.NumberElement

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/19 23:21
 */
class NumberParser extends Parsec[Any, Char] {
  val parser: Decimal = decimal
  override def ask(s: State[Char]): Either[Exception, Any] = {
    parser ? s map {result => NumberElement(result.toDouble)}
  }
}