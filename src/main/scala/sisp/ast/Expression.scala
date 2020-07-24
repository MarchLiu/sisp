package sisp.ast

/**
 * S Expression
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:28
 */
class Expression(val elements: Seq[Any]) extends Element {
  override def eval(env: Env): Either[Exception, Double] = {
    val funcName = elements.head.asInstanceOf[String]
    val params = elements drop 1 map {_.asInstanceOf[Element].eval(env)}
    env.get(funcName).flatMap(_.asInstanceOf[Lambda].apply(params))
  }
}
