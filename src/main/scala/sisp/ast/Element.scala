package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:26
 */
trait Element {
  def eval(env: Env): Either[Exception, Any]
}
