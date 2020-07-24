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
  val number: Try[Any, Char] = attempt(new NumberParser)
  override def ask(s: State[Char]): Either[Exception, Any] = {
    val parser: Parsec[Any, Char] = attempt(new ExprParser) <|> attempt(number) <|> attempt(new NameParser)
    parser ? s
  }
}
