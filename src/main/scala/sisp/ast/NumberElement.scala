package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:27
 */
case class NumberElement(num: Double) extends Element {
  override def eval(env: Env): Either[Exception, Any] = Right(num)
}
