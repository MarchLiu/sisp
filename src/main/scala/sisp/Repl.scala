package sisp

import jaskell.parsec.ParsecException
import sisp.ast.{Add, Divide, Element, Env, Sub}
import sisp.parsers.{NumberParser, Parser}

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

  val parser = new Parser
  val prmt = ">> "

  val env = new Env;
  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)

  def main(args: Array[String]) {
    while (true) {
      print(prmt)
      val line = readLine()
      parser ask line match {
        case Right(ast) => ast.asInstanceOf[Element].eval(env) foreach println
        case Left(error) => println(s"invalid number [$line] error [${error.getMessage}]")
      }
    }
  }

}
