package sisp.parsers

import jaskell.parsec.{Parsec, State}
import jaskell.parsec.Atom.pack
import jaskell.parsec.Txt.ch
import sisp.ast.Quote

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:42
 */
class QuoteParser extends Parsec[Char, Any]{
  override def ask(s: State[Char]): Try[Any] = {
    val parser = new Parser
    val psc = ch('\'') >> parser >>= {value => pack(new Quote(value))}
    psc ? s
  }
}