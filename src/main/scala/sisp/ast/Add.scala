package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/21 19:23
 */
class Add extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Double] = {
    prepare(env, params).map(_.asInstanceOf[Seq[Double]].sum)
  }
}
