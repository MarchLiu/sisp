package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/03 18:50
 */
class Let extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    val declares = params.head
    if (!declares.isInstanceOf[Expression]) {
      return Left(new ParserException(s"first element of let must be declare but now is $declares"))
    }
    val environment = new Env
    environment.global = Some(env)
    val vars = declares.asInstanceOf[Expression].elements
    if(vars.size % 2 != 0){
      return Left(new ParserException(s"declare block must have even items"))
    }
    for (pair <- vars.sliding(2, 2)) {
      if (!pair.head.isInstanceOf[Name]) {
        return Left(new ParserException(s"invalid declare, first element must be a name but [${pair.head}]"))
      }
      val name = pair.head.asInstanceOf[Name]
      environment.eval(pair.last) flatMap { value => Right(environment.put(name.name, value)) } match {
        case left: Left[_, _] => return left
        case right: Right[_, _] => right
      }
    }

    params.drop(1).dropRight(1) foreach environment.eval
    environment eval params.last
  }
}
