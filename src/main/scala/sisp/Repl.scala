package sisp

import jaskell.parsec.State

import scala.io.StdIn.readLine

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/16 22:20
 */
object Repl {
  import jaskell.parsec.Txt._

  val prmt = ">> "
  def main(args: Array[String]) {
    while (true) {
      print(prmt)
      val line:State[Char] = readLine()

    }
  }
}
