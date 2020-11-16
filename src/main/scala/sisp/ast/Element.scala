package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:26
 */
trait Element {
  def eval(env: Env): Try[Any]
}
