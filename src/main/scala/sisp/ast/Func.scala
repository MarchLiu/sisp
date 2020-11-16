package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 20:55
 */
class Func(val args: Seq[Name], val body: Seq[Any], our:Env) extends Recurable {
  override def invoke(env: Env, params: Seq[Any]): Try[Any] = {
    val environment = new Env
    val local = our.copy()
    local.global = Some(env)
    environment.global = Some(local)
    if(params.size != args.size) {
      return Failure(new ParserException(s"the function invoke need ${args.size} parameters bug given ${params.size}"))
    }
    args zip params foreach {pair =>
      val (name, parameter) = pair
      env.eval(parameter) foreach { value =>
        environment.put(name.name, value)
      }
    }
    for(expr <- body dropRight 1){
      environment.eval(expr)
    }
    environment.eval(body.last)
  }
}
