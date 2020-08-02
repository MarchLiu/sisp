package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 16:30
 */
trait Compare extends Lambda {
  val and = new And
  def cmp(x: Any, y: Any): Either[Exception, Boolean]
  def compare(seq: Seq[Any]): Either[Exception, Boolean] = {
    if(seq.size < 2){
      return Left(new ParserException(s"can't compare ${seq.size} args, need more args for compare"))
    }

    sequenceU(seq.sliding(2).map(pair => cmp(pair.head, pair.last)).toSeq).map {_ forall identity}

  }

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = prepare(env, params).flatMap(compare)
}
