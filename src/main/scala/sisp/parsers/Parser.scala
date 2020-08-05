package sisp.parsers

import jaskell.parsec.Combinator.attempt
import jaskell.parsec.{Parsec, State, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 16:44
 */
class Parser extends Parsec[Any, Char]{
  lazy val expr: Try[Any, Char] = attempt(new ExprParser)
  val number: Try[Any, Char] = attempt(new NumberParser)
  val string: Try[Any, Char] = attempt(new StringParser)
  val quote: Try[Any, Char] = attempt(new QuoteParser)
  val name: Try[Any, Char] = attempt(new NameParser)
  override def ask(s: State[Char]): Either[Exception, Any] = {
    val parser: Parsec[Any, Char] = quote <|> expr <|> number <|> string <|> name
    parser ? s
  }
}
