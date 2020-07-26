package sisp.ast

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/26 14:55
 */
class Name(val name:String) extends Element {
  override def eval(env: Env): Either[Exception, Any] = env.get(name)
}
