package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 20:44
 */
class Get extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if ((params.size) < 2 || (params.size > 3)) {
      return Left(new ParserException(s"get require 2 or 3 params but [$params]"))
    }

    val default: () => Either[Exception, Any] = {() =>
      if (params.size == 3) {
        env.eval(params)
      } else {
        Right(Nil)
      }
    }

    val expr = for {
      coll <- env.eval(params.head)
      key <- env.eval(params(1))
    } yield {(coll, key)}

    expr flatMap {
        case (expr: Expression, idx: Int) =>
          if (expr.elements.size > idx) Right(expr.elements(idx)) else default()
        case (seq: Seq[_], idx: Int) => if (seq.nonEmpty) Right(seq(idx)) else default()
        case (list: java.util.List[_], idx:Int)  => if (list.size() > 0) Right(list.get(idx)) else default()
        case (map: Map[_, _], key)  => if(map contains key.asInstanceOf) Right(map.get(key.asInstanceOf)) else default()
        case (map: java.util.Map[_, _], key)  => if(map containsKey key) Right(map.get(key)) else default()

        case _ => Left(new ParserException(s"(head ${params.head}) unsupport"))
      }
    }
  }
}
