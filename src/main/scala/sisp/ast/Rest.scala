package sisp.ast

import sisp.ParserException

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/08/05 19:59
 */
class Rest extends Lambda {
  override def apply(env: Env, params: Seq[Any]): Either[Exception, Any] = {
    if (params.size != 1) {
      return Left(new ParserException(s"head function should accept only one parameter, but $params given"))
    }
    env.eval(params.head) map {
      case expr: Expression => if (expr.elements.nonEmpty) expr.elements.tail else new Quote(new Expression(Seq()))
      case seq: Seq[_] => if (seq.nonEmpty) seq.tail else Seq()
      case list: java.util.List[_] => if (list.size() > 0) list.subList(1, list.size()) else Nil
      case _ => Left(new ParserException(s"(head ${params.head}) unsupport"))
    }
  }
}
