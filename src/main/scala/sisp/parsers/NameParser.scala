package sisp.parsers

import jaskell.parsec.Atom.{is, pack}
import jaskell.parsec.Combinator.many1
import jaskell.parsec.{Parsec, State}
import jaskell.parsec.Txt.mkString
import sisp.ast.Name
/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 16:48
 */
class NameParser extends Parsec[Any, Char] {
  val predicate:Function[Char, Boolean] = {c => !(c==')'||c.isWhitespace)}
  val parser: Parsec[Name, Char] = many1(is(predicate)) >>= mkString >>= { name =>
    pack(new Name(name))
  }

  override def ask(s: State[Char]): Either[Exception, Any] = parser ? s
}
