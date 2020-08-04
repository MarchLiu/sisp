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
    val elseStat = {
      if (params.size % 2 == 0) {
        null
      } else {
        env.eval(params.last)
      }
    }
    val dispatch = if(params.size %2 == 0) params else params.dropRight(1)
    for (pair <- dispatch.sliding(2, 2)) {
      if(IsTrue.isTrue(env.eval(pair.head)))
    }
  }
}
