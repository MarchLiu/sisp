package sisp.parsers

import jaskell.parsec.Atom.is
import jaskell.parsec.Combinator.many1
import jaskell.parsec.{Parsec, State}
import jaskell.parsec.Txt.mkString
/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 16:48
 */
class NameParser extends Parsec[Any, Char] {
  val predicate:Function[Char, Boolean] = {c => !(c==')'||c.isWhitespace)}
  val parser: Parsec[String, Char] = many1(is(predicate)) >>= mkString

  override def ask(s: State[Char]): Either[Exception, Any] = parser ? s
}
