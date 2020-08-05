package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:07
 */
class Recur extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    sequenceU(params map env.eval) map RecurExpression
  }
}

case class RecurExpression(params: Seq[Any])
