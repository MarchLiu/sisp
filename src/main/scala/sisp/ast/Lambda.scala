package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 20:59
 */
trait Lambda {
  val name: String

  // scalaz 有此功能的封装，这里为了不额外引入复杂性，手工实现一个
  def sequenceU(params: Seq[Either[String, Any]]): Either[String, List[Double]] =
    params.foldRight(Right(Nil): Either[String, List[Double]]) { (elem, acc) =>
      for {
        xs <- acc
        x <- elem
      } yield x.asInstanceOf[Double] :: xs
    }

  def apply(params: Seq[Either[String, Any]]): Either[String, Double]

}


