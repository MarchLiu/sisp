package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 19:23
 */
class Add extends Lambda {
  override val name: String = "+"

  override def apply(params: Seq[Either[String, Any]]): Either[String, Double] = {
    sequenceU(params).map(_.sum)
  }
}
