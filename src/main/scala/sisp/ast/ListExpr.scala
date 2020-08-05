package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:53
 */
class ListExpr extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    sequenceU(params map env.eval) map {values => new Quote(new Expression(values))}
  }
}
