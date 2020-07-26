package sisp.ast

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 20:59
 */
trait Lambda {

  def prepare(env: Env, params: Seq[Any]): Either[Exception, Seq[Any]] = {
    params.foldRight(Right(Nil): Either[Exception, List[Any]]) { (param, acc) =>
      for {
        xs <- acc
        x <- param match {
          case element: Element =>
            element.eval(env)
          case _ =>
            Right(param)
        }
      } yield x :: xs
    }
  }

  def apply(env: Env, params: Seq[Any]): Either[Exception, Any]
}


