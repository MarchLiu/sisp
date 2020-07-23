package sisp.parsers

import jaskell.parsec.Atom.is
import jaskell.parsec.{Parsec, ParsecException, State}
import jaskell.parsec.Combinator.many1
import jaskell.parsec.Txt.{mkString, noWhitespace}

import scala.collection.mutable
import scala.util.control.Breaks
/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/22 16:48
 */
class NameParser extends Parsec[Any, Char] {
  val predicate:Function[Char, Boolean] = {c => !(c==')'||c.isWhitespace)}
  val parser: Parsec[String, Char] = many1(is[Char](predicate)) >>= { chars =>
    { _ =>
      Right(chars.mkString)
    }
  }

  override def ask(s: State[Char]): Either[Exception, Any] = parser ? s
}
