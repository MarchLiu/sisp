package sisp.ast

import sisp.ParserException

import scala.collection.mutable
import scala.collection.mutable.HashMap;

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 19:01
 */
class Env {
  var global: Option[Env] = None
  val local = new mutable.HashMap[String, Any]()

  def put(name: String, lambda: Any): Option[Any] = local.put(name, lambda)

  def findUp(name: String): Either[Exception, Any] = {
    global.toRight(new ParserException(s"$name not found")).flatMap(_.get(name))
  }

  def findIn(name: String): Either[Exception, Any] = {
    local.get(name).toRight(new ParserException(s"$name not found in local environment"))
  }

  def get(name: String): Either[Exception, Any] = {
    findIn(name).orElse(findUp(name))
  }

  def eval(param: Any): Either[Exception, Any] = {
    param match {
      case elem: Element => elem.eval(this)
      case result: Either[Exception, Any] => result
      case _ => Right(param)
    }
  }
}
