package sisp

import scala.io.StdIn.readLine

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/16 22:20
 */
object Repl {
  val prmt = ">> "
  def main(args: Array[String]) {
    print(prmt)
    val line = readLine()
    println(line)
  }
}
