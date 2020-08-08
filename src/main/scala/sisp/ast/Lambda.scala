package sisp.ast

import sisp.ParserException

import scala.jdk.javaapi.CollectionConverters


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

  def sequenceU[T](params: Seq[Either[Exception, T]]): Either[Exception, List[T]] =
    params.foldRight(Right(Nil): Either[Exception, List[T]]) { (elem, acc) =>
      for {
        xs <- acc
        x <- elem
      } yield x.asInstanceOf[T] :: xs
    }

  def isList(obj: Any): Boolean = {
    obj.isInstanceOf[Seq[_]] || obj.isInstanceOf[List[_]] ||
      (obj.isInstanceOf[Quote] && obj.asInstanceOf[Quote].value.isInstanceOf[Expression])
  }

  def elements(obj: Any): Either[Exception, Seq[Any]] = obj match {
    case seq: Seq[_] => Right(seq.asInstanceOf)
    case list: java.util.List[_] => Right(CollectionConverters.asScala(list).toSeq)
    case quote: Quote if quote.value.isInstanceOf[Expression] => Right(quote.value.asInstanceOf[Expression].elements)
    case _ => Left(new ParserException(s"$obj is't a valid list"))
  }

  def apply(env: Env, params: Seq[Any]): Either[Exception, Any]


}


