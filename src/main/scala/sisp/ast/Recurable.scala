package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 21:02
 */
trait Recurable extends Lambda {
  def invoke(env: Env, params: Seq[Any]):Try[Any]
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    var result = invoke(env, params)
    while(result.isSuccess && result.get.isInstanceOf[RecurExpression]) {
      result = result.flatMap(recur => invoke(env, recur.asInstanceOf[RecurExpression].params))
    }
    result
  }
}
