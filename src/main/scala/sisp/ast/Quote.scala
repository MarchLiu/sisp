package sisp.ast

import scala.util.{Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:41
 */
class Quote(val value: Any) extends Element {
  override def eval(env: Env): Try[Any] = Success(value)
}

object Quote {
  def fromSeq(seq: Seq[_]): Quote = {
    new Quote(new Expression(seq))
  }
}