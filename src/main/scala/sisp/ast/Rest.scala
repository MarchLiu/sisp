package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:59
 */
class Rest extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    if (params.size != 1) {
      return Failure(new ParserException(s"rest function should accept only one parameter, but $params given"))
    }

    env.eval(params.head) flatMap { param =>
      if(!isList(param)){
        return Failure(new ParserException(s"rest function require one list, but $param given"))
      }
      elements(param) flatMap { seq =>
        return Success(Quote.fromSeq(seq))
      }
    }
  }
}
