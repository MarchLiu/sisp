package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:59
 */
class Rest extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (params.size != 1) {
      return Left(new ParserException(s"rest function should accept only one parameter, but $params given"))
    }

    env.eval(params.head) flatMap { param =>
      if(!isList(param)){
        return Left(new ParserException(s"rest function require one list, but $param given"))
      }
      elements(param) flatMap { seq =>
        return Right(Quote.fromSeq(seq))
      }
    }
  }
}
