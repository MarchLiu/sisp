package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/08 15:23
 */
class Conj extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (params.size != 2 || !params(0).isInstanceOf[Seq[Any]]) {
      return Left(new ParserException(s"conj's formal (conj seq x) but get (conj $params)"))
    }

    for {
      values <- env eval params.head
      value <- env eval params(1)
    } yield {
      values.asInstanceOf[Seq[Any]].appended(value)
    }
  }
}
