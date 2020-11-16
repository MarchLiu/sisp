package sisp.ast

import sisp.ParserException

import scala.collection.mutable
import scala.collection.mutable.HashMap
import scala.util.{Failure, Success, Try};

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

  def findUp(name: String): Try[Any] = {
    global.map(_ get name).getOrElse(Failure(new ParserException(s"$name not found")))
  }

  def findIn(name: String): Try[Any] = {
    local.get(name).map(re => Success(re))
      .getOrElse[Try[Any]](Failure(new ParserException(s"$name not found in local environment")))
  }

  def get(name: String): Try[Any] = {
    findIn(name).orElse(findUp(name))
  }

  def eval(param: Any): Try[Any] = {
    param match {
      case elem: Element => elem.eval(this)
      case result: Try[Any] => result
      case _ => Success(param)
    }
  }

  def copy(): Env = {
    val re = new Env
    re.global = None
    re.local.addAll(this.local)
    re
  }
}
