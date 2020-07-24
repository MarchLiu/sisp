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
  val map = new mutable.HashMap[String, Any]()
  def put(name: String, lambda: Any): Option[Any] = map.put(name, lambda)
  def get(name: String):Either[Exception, Any] = map.get(name).toRight(new ParserException(s"$name not found"))
}
