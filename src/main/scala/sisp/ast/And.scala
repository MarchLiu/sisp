package sisp.ast

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 18:07
 */
class And extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Try[Boolean] = prepare(env, params) flatMap { elements =>
    for (item <- params) {
      env.eval(item) map IsTrue.isTrue match {
        case failure: Failure[_] => return failure
        case Success(false) => return Success(false)
        case Success(true) => Success(true)
      }
    }

    return Success(true)
  }
}
