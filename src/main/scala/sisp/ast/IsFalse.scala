package sisp.ast

import java.util

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/30 17:53
 */
class IsFalse  extends IsTrue {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Boolean] = {
    if(params.size != 1){
      return Left(new ParserException(s"true? function require single parameter"))
    }
    env.eval(params.head) map {result => !IsTrue.isTrue(result)}
  }
}
