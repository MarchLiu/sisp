package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 14:55
 */
class Name(val name:String) extends Element {
  override def eval(env: Env): Try[Any] = env.get(name)
}
