package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 18:41
 */
class Quote(val value: Any) extends Element {
  override def eval(env: Env): Either[Exception, Any] = Right(value)
}

object Quote {
  def fromSeq(seq: Seq[_]): Quote = {
    new Quote(new Expression(seq))
  }
}