package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:48
 */
class If extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    if (params.size < 2 || params.size > 3) {
      return Failure(new ParserException(s"invalid if statement (if $params), parameters size should be 2 or 3"))
    }

    env.eval(params.head) map IsTrue.isTrue flatMap { check =>
      if (check) {
        env.eval(params(1))
      } else {
        if (params.size == 3) {
          env.eval(params(2))
        } else {
          Success(Nil)
        }
      }
    }
  }
}
