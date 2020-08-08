package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:08
 */
class Great extends Compare {
  override def cmp(x: Any, y: Any): Either[Exception, Boolean] = {
    x match {
      case value: Number if y.isInstanceOf[Number] => Right(value.doubleValue() > y.asInstanceOf[Number].doubleValue())
      case value: Ordered[_] if y.isInstanceOf[x.type] =>
        Right(value.compareTo(y.asInstanceOf) > 0)
      case _ =>
        Left(new ParserException(s"$x and $y are type of [$x.type] that is't comparable"))
    }
  }
}
