package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 14:48
 */

class Def extends Lambda {

  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    val name = params.head.asInstanceOf[Name].name
    if(env.findIn(name).isRight){
      return Left(new ParserException(s"$name exists"))
    }
    val result = params(1) match {
      case element: Element => element.eval(env)
      case _ => params(1)
    }
    result match {
      case Right(value) =>
        env.put(name, value)
        Right(value)
      case left: Left[Exception, Any] => left
      case _ =>
        env.put(name, result)
        Right(result)
    }
  }
}
