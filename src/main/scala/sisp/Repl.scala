package sisp

import jaskell.expression.Env

import scala.io.StdIn.readLine
import jaskell.expression.parsers.Parser
import jaskell.parsec.State

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
  val parser = new Parser()
  val env = new Env()
  def main(args: Array[String]) {
    while (true) {
      print(prmt)
      val line:State[Char] = readLine()

      (parser ? line) flatMap {_.makeAst eval env} match {
        case Right(re) =>
          println(re)
        case Left(error) =>
          println(s"$line parse error ${error.getMessage}")
      }
    }
  }
}
