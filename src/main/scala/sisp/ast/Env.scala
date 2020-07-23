package sisp.ast

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
  def get(name: String):Either[String, Any] = map.get(name).toRight(s"$name not found")
}
