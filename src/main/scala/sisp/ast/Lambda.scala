package sisp.ast

import sisp.ParserException

import scala.jdk.javaapi.CollectionConverters
import scala.util.{Failure, Success, Try}


/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 20:59
 */
trait Lambda {

  def prepare(env: Env, params: Seq[Any]): Try[Seq[Any]] = {
    params.foldRight(Success(Nil): Try[List[Any]]) { (param, acc) =>
      for {
        xs <- acc
        x <- param match {
          case element: Element =>
            element.eval(env)
          case _ =>
            Success(param)
        }
      } yield x :: xs
    }
  }

  def sequenceU[T](params: Seq[Try[T]]): Try[List[T]] =
    params.foldRight(Try(List[T]())) { (elem, acc) =>
      for {
        xs <- acc
        x <- elem
      } yield x.asInstanceOf[T] :: xs
    }

  def isList(obj: Any): Boolean = {
    obj.isInstanceOf[Seq[_]] || obj.isInstanceOf[List[_]] ||
      (obj.isInstanceOf[Quote] && obj.asInstanceOf[Quote].value.isInstanceOf[Expression])
  }

  def elements(obj: Any): Try[Seq[Any]] = obj match {
    case seq: Seq[_] => Success(seq.asInstanceOf)
    case list: java.util.List[_] => Success(CollectionConverters.asScala(list).toSeq)
    case quote: Quote if quote.value.isInstanceOf[Expression] => Success(quote.value.asInstanceOf[Expression].elements)
    case _ => Failure(new ParserException(s"$obj is't a valid list"))
  }

  def apply(env: Env, params: Seq[Any]): Try[Any]


}


