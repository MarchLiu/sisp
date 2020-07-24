package sisp.parsers

import jaskell.parsec.{Is, Parsec, State}
import jaskell.parsec.Combinator.{between, many, attempt}
import jaskell.parsec.Atom.{is, one}
import jaskell.parsec.Txt.{ch, mkString}
import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/24 15:55
 */
class StringParser extends Parsec[Any, Char] {
  val predicate: Char => Boolean = { c: Char => c != '"' && c != '\\' }
  val char: Parsec[Char, Char] = is(predicate)
  val escapeChar: Parsec[Char, Char] = attempt(ch('\\') >> { s =>
    one ? s flatMap {
      case 'n' => Right('\n')
      case 't' => Right('\t')
      case 'r' => Right('\r')
      case '"' => Right('"')
      case '\\' => Right('\\')
      case c => Left(new ParserException(s"expect a escape char but get \\$c"))
    }
  })
  lazy val parser: Parsec[String, Char] = between(ch('"'), ch('"'), many(escapeChar <|> char)) >>= mkString

  override def ask(s: State[Char]): Either[Exception, String] = {
    parser ? s
  }
}
