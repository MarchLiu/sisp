package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 17:48
 */
class If extends Lambda {
  val isTrue = new IsTrue

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (params.size < 2 || params.size > 3) {
      return Left(new ParserException(s"invalid if statement (if $params), parameters size should be 2 or 3"))
    }
    isTrue.apply(env, Seq(params.head)) flatMap { cond =>
      if (cond) {
        env.eval(params(1))
      } else {
        if (params.size == 3) {
          env.eval(params(2))
        } else {
          Right(Nil)
        }
      }
    }
  }
}
