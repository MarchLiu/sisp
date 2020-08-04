package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 14:58
 */
class Cond extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    val isEvent = params.size % 2 == 0
    val elseStat: () => Either[Exception, Any] = () => {
      if (isEvent) {
        Right(null)
      } else {
        env.eval(params.last)
      }
    }

    val dispatch = if (isEvent) params else params.dropRight(1)
    dispatch.sliding(2, 2).map(pair => (env.eval(pair.head), pair.last)) collectFirst {
      case (Right(true), expr) => env.eval(expr)
    } getOrElse elseStat()
  }
}
