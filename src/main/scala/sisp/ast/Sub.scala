package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 19:23
 */
class Sub extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Try[Any] =
    prepare(env, params).map(_.asInstanceOf[Seq[Double]].reduce((x, y)=> x - y))
}
