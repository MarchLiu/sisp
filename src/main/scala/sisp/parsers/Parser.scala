package sisp.parsers

import jaskell.parsec.Combinator.attempt
import jaskell.parsec.{Parsec, State}

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 16:44
 */
class Parser extends Parsec[Char, Any]{
  lazy val expr: Parsec[Char, Any] = attempt(new ExprParser)
  val number: Parsec[Char, Any] = attempt(new NumberParser)
  val string: Parsec[Char, Any] = attempt(new StringParser)
  val quote: Parsec[Char, Any] = attempt(new QuoteParser)
  val name: Parsec[Char, Any] = attempt(new NameParser)
  override def ask(s: State[Char]): Try[Any] = {
    val parser: Parsec[Char, Any] = quote <|> expr <|> number <|> string <|> name
    parser ? s
  }
}
