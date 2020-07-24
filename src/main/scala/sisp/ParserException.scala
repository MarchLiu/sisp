package sisp

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/24 14:34
 */
class ParserException(val message: String) extends Exception {
  override def getMessage: String = message
}
