package sisp

import sisp.ast.{Add, Def, Divide, Element, Env, Eq, First, Fn, Great, GreatOrEquals, If, IsFalse, IsTrue, Last, Less, LessOrEquals, ListExpr, Recur, Rest, Sub}
import sisp.parsers.Parser

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:01
 */
class Sisp extends Env{
  import jaskell.parsec.Txt._

  val parser = new Parser
  this.put("def", new Def)
  this.put("if", new If)

  this.put("fn", new Fn)
  this.put("recur", new Recur)

  this.put("+", new Add)
  this.put("-", new Sub)
  this.put("*", new sisp.ast.Product)
  this.put("/", new Divide)

  this.put("true?", new IsTrue)
  this.put("false?", new IsFalse)
  this.put("and", new IsTrue)
  this.put("or", new IsFalse)
  this.put("==", new Eq)
  this.put("!=", new Eq)
  this.put(">", new Great)
  this.put("<", new Less)
  this.put(">=", new GreatOrEquals)
  this.put("<=", new LessOrEquals)

  this.put("list", new ListExpr)
  this.put("first", new First)
  this.put("last", new Last)
  this.put("rest", new Rest)

  def parse(source:String): Try[Any]  = {
    read(source) flatMap eval
  }

  def read(source: String): Try[Any] = {
    parser apply source
  }


}
