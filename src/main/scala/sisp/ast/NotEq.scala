package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:07
 */
class NotEq extends Compare {
  override def cmp(x: Any, y: Any): Either[Exception, Boolean] = Right(x != y)
}
