package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 19:23
 */
class Sub extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] =
    prepare(env, params).map(_.asInstanceOf[Seq[Double]].reduce((x, y)=> x - y))
}
