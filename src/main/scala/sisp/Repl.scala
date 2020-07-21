package sisp

import jaskell.parsec.ParsecException
import sisp.parsers.NumberParser

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
  val parser = new NumberParser
  val prmt = ">> "
  def main(args: Array[String]) {
    while (true) {
      print(prmt)
      val line = readLine()
      parser ask line match {
        case Right(result) => println(result)
        case Left(error) => println(s"invalid number [$line] error [${error.getMessage}]")
      }
    }
  }
}
