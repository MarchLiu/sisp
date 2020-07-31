package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 18:07
 */
class And extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Boolean] = prepare(env, params) flatMap { elements =>
    for (item <- params) {
      env.eval(item) map IsTrue.isTrue match {
        case left: Left[_, _] => return left
        case Right(false) => return Right(false)
        case Right(true) => Right(true)
      }
    }

    return Right(true)
  }
}
