package sisp.ast

import scala.util.{Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 14:58
 */
class Cond extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    val isEvent = params.size % 2 == 0
    val elseStat: () => Try[Any] = () => {
      if (isEvent) {
        Success(null)
      } else {
        env.eval(params.last)
      }
    }

    val dispatch = if (isEvent) params else params.dropRight(1)
    dispatch.sliding(2, 2).map(pair => (env.eval(pair.head), pair.last)) collectFirst {
      case (Success(true), expr) => env.eval(expr)
    } getOrElse elseStat()
  }
}
