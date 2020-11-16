package sisp.ast

import scala.util.Try

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 14:56
 */
class Do extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    val environment:Env = new Env
    environment.global = Some(env)

    params.dropRight(1).foreach {environment.eval}
    environment eval params.last
  }
}
