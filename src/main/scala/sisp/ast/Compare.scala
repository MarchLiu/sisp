package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/28 16:30
 */
trait Compare extends Lambda {
  val and = new And
  def cmp(x: Any, y: Any): Try[Boolean]
  def compare(seq: Seq[Any]): Try[Boolean] = {
    if(seq.size < 2){
      return Failure(new ParserException(s"can't compare ${seq.size} args, need more args for compare"))
    }

    sequenceU(seq.sliding(2).map(pair => cmp(pair.head, pair.last)).toSeq).map {_ forall identity}

  }

  override def apply(env: Env, params: Seq[Any]): Try[Any] = prepare(env, params).flatMap(compare)
}
