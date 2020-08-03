package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 14:56
 */
class Do extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    params.drop(1).foreach {env.eval}
    env.eval(params.last)
  }
}
