package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/08 15:23
 */
class Conj extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    if (params.size != 2) {
      return Failure(new ParserException(s"conj's formal (conj seq x) but get (conj $params)"))
    }

    env.eval(params.head) flatMap { seq =>
      if (!isList(seq)) {
        return Failure(new ParserException(s"conj's formal (conj seq x) but get (conj $seq)"))
      }

      for {
        values <- elements(params.head)
        value <- env eval params(1)
      } yield {
        Quote.fromSeq(values.asInstanceOf[Seq[Any]].appended(value))
      }
    }
  }
}
