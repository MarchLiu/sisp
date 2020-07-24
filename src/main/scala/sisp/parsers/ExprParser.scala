package sisp.parsers

import jaskell.parsec.Atom.pack
import jaskell.parsec.Combinator.{between, sepBy1}
import jaskell.parsec.Txt.{ch, skipWhiteSpaces}
import jaskell.parsec.{Parsec, SkipWhitespaces, State}
import sisp.ast.{Element, Expression}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:21
 */
class ExprParser extends Parsec[Any, Char]{
  val skip: SkipWhitespaces = skipWhiteSpaces
  val elementParser = new Parser

  override def ask(s: State[Char]): Either[Exception, Element] = {
    val parser =
      between(ch('(') >> skip, skip >> ch(')'), sepBy1(elementParser, skip)) >>=
        {vals => pack(new Expression(vals))}
    parser ? s
  }
}
