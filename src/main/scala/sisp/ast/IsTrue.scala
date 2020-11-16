package sisp.ast

import java.util

import sisp.ParserException

import scala.collection.View.Collect
import scala.util.{Failure, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 17:45
 */
class IsTrue extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Boolean] = {
    if(params.size != 1){
      return Failure(new ParserException(s"true? function require single parameter"))
    }
    env.eval(params.head) map IsTrue.isTrue
  }

}

object IsTrue {
  // isTrue not eval anything, just check it
  def isTrue(param: Any) : Boolean = param match {
    case boolean: Boolean => boolean
    case number: Number => number.intValue() != 0
    case either: Either[_,  _] => either.isRight
    case opt: Option[_] => opt.isDefined
    case coll: util.Collection[_] => !coll.isEmpty
    case seq: Seq[_] => seq.nonEmpty
    case map: Map[_, _] => map.nonEmpty
    case set: Set[_] => set.nonEmpty
    case str: String => str.nonEmpty
    case Nil => false
    case null => false
    case _ => true
  }
}
