package sisp.ast

import scala.util.{Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:27
 */
case class NumberElement(num: Double) extends Element {
  override def eval(env: Env): Try[Any] = Success(num)
}
