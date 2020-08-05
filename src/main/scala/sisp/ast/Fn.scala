package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/04 18:43
 */
class Fn extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (!params.head.isInstanceOf[Expression]) {
      return Left(new ParserException(s"args list must be a list but [${params.head}]"))
    }
    val parameters = params.head.asInstanceOf[Expression].elements
    for (p <- parameters) {
      if (!p.isInstanceOf[Name]) {
        return Left(new ParserException(s"args list must all names but given $p"))
      }
    }

    val args = parameters.map(_.asInstanceOf[Name])
    scan(env, args, params.tail) flatMap { env =>
      Right(new Func(args, params.tail, env))
    }
  }

  def scan(env: Env, params: Seq[Name], body: Seq[Any]): Either[Exception, Env] = {
    val book = new Env
    book.global = Some(env)
    params foreach { p => book.put(p.name, p) }
    val result = new Env
    result.global = Some(book)

    for (stat <- body) {
      stat match {
        case name: Name => result.get(name.name) match {
          case _: Left[_, _] => return Left(new ParserException(s"function define failed, ${name.name} not found"))
          case _: Right[_, _] if (book.findIn(name.name).isLeft) =>
            result.get(name.name) foreach {
              result.put(name.name, _)
            }
            stat
        }
        case expr: Expression => scanExpr(book, result, expr) match {
          case left: Left[_, _] => return left
          case _ => stat
        }
        case _ => stat
      }
    }

    result.global = None
    Right(result)
  }

  def scanExpr(our: Env, env: Env, expr: Expression): Either[Exception, Env] = {
    expr.elements.head match {
      case name: Name if env.get(name.name).exists(_.isInstanceOf[Let]) =>
        val vars = expr.elements(1).asInstanceOf[Expression]
        vars.elements.sliding(2, 2) foreach { pair =>
          val name = pair.head.asInstanceOf[Name]
          our.put(name.name, name)
        }
      case name: Name if env.get(name.name).exists(_.isInstanceOf[Def]) =>
        val name = expr.elements(1).asInstanceOf[Name]
        our.put(name.name, name)
      case _ =>
    }
    for (name <- expr.elements.filter(_.isInstanceOf[Name]).map(_.asInstanceOf[Name])) {
      if (env.get(name.name).isLeft) {
        return Left(new ParserException(s"function define failed, ${name.name} not found"))
      }
      if (our.findIn(name.name).isLeft) {
        env.get(name.name) foreach { value => env.put(name.name, value) }
      }
    }
    val check:Option[Either[Exception, Env]] = expr.elements.filter(_.isInstanceOf[Expression])
      .map(e => scanExpr(our, env, e.asInstanceOf[Expression]))
      .collectFirst({ case left: Left[_, _] => left })
    check.getOrElse(Right(env))
  }
}
