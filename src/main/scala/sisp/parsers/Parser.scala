package sisp.parsers

import jaskell.parsec.{Parsec, SkipWhitespaces, State, Try}
import jaskell.parsec.Combinator.{attempt, between, choice, sepBy1}
import jaskell.parsec.Txt.{ch, skipWhiteSpaces}
import sisp.ast.Expression

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
    val parser: Parsec[Any, Char] = choice(attempt(new ExprParser), number, attempt(new NameParser))
    parser ? s
  }
}
