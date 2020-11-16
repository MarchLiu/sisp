package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:53
 */
class ListExpr extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    sequenceU(params map env.eval) map {values => new Quote(new Expression(values))}
  }
}
