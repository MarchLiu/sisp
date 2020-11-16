package sisp.ast

import scala.util.Try

/**
 * S Expression
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 18:28
 */
class Expression(val elements: Seq[Any]) extends Element {
  override def eval(env: Env): Try[Any] = {
    val func = elements.head.asInstanceOf[Element]
    val params = elements drop 1
    func.eval(env).flatMap(_.asInstanceOf[Lambda].apply(env, params))
  }
}
