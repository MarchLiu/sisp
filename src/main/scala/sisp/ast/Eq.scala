package sisp.ast

import scala.util.{Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 16:50
 */
class Eq extends Compare {
  override def cmp(x: Any, y: Any): Try[Boolean] = Success(x == y)
}
