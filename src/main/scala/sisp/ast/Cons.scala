package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/08 15:23
 */
class Cons extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (params.size != 2) {
      return Left(new ParserException(s"cons's formal (cons x seq) but get (cons $params)"))
    }

    env.eval(params(1)).flatMap { seq =>
      if(!isList(seq)) {
        return Left(new ParserException(s"cons's formal (cons x seq) but get (cons $seq)"))
      }
      for {
        value <- env eval params.head
        values <- elements(seq)
      } yield Quote.fromSeq(Seq(value) ++ values.asInstanceOf)
    }
  }
}
