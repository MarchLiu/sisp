package sisp

import sisp.ast.{Add, Def, Divide, Element, Env, Eq, Fn, Great, GreatOrEquals, If, IsFalse, IsTrue, Less, LessOrEquals, Recur, Sub}
import sisp.parsers.Parser

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

  def parse(source:String): Either[Exception, Any]  = {
    read(source) flatMap eval
  }

  def read(source: String): Either[Exception, Any] = {
    parser ask source
  }


}
