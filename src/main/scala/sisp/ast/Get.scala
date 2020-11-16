package sisp.ast

import sisp.ParserException

import scala.util.{Failure, Success, Try}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:44
 */
class Get extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Try[Any] = {
    if ((params.size) < 2 || (params.size > 3)) {
      return Failure(new ParserException(s"get require 2 or 3 params but [$params]"))
    }

    val default: () => Try[Any] = { () =>
      if (params.size == 3) {
        env.eval(params)
      } else {
        Success(Nil)
      }
    }

    val expr = for {
      coll <- elements(env.eval(params.head))
      key <- env.eval(params(1))
    } yield {
      (coll, key)
    }

    expr flatMap {
      case (expr: Expression, idx: Int) =>
        if (expr.elements.size > idx) Success(expr.elements(idx)) else default()
      case (seq: Seq[_], idx: Int) => if (seq.nonEmpty) Success(seq(idx)) else default()
      case (map: Map[_, _], key) => if (map contains key.asInstanceOf) Success(map.get(key.asInstanceOf)) else default()
      case (map: java.util.Map[_, _], key) => if (map containsKey key) Success(map.get(key)) else default()

      case _ => Failure(new ParserException(s"(head ${params.head}) unsupport"))
    }
  }
}

