package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:08
 */
class Great extends Compare {
  override def cmp(x: Any, y: Any): Try[Boolean] = {
    x match {
      case value: Number if y.isInstanceOf[Number] =>
        Success(value.doubleValue() > y.asInstanceOf[Number].doubleValue())
      case value: Ordered[_] if y.isInstanceOf[x.type] =>
        Success(value.compareTo(y.asInstanceOf) > 0)
      case _ =>
        Failure(new ParserException(s"$x and $y are type of [$x.type] that is't comparable"))
    }
  }
}
