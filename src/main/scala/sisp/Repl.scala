package sisp

import jaskell.parsec.ParsecException
import sisp.ast.{Add, Def, Divide, Element, Env, Eq, Great, GreatOrEquals, Less, LessOrEquals, Sub}
import sisp.parsers.{NumberParser, Parser}

import scala.io.StdIn.readLine
import scala.util.{Failure, Success}

/**
 * TODO
 *
 * @author mars
 * @version 1.0.0
 * @since 2020/07/16 22:20
 */
object Repl {
  import jaskell.parsec.Parsec._
  import jaskell.parsec.Txt._

  val parser = new Parser
  val prmt = ">> "

  val env = new Env
  env.put("def", new Def)

  env.put("+", new Add)
  env.put("-", new Sub)
  env.put("*", new sisp.ast.Product)
  env.put("/", new Divide)
  env.put("==", new Eq)
  env.put("!=", new Eq)
  env.put(">", new Great)
  env.put("<", new Less)
  env.put(">=", new GreatOrEquals)
  env.put("<=", new LessOrEquals)

  def main(args: Array[String]) {

    while (true) {
      print(prmt)
      val line = readLine()
      parser apply  line  flatMap  {
        case element: Element => element.eval(env)
        case result: Failure[Any] => result
        case any => Success(any)
      } match {
        case Success(result) => println(result)
        case Failure(error) => println(s" [$line] error [${error.getMessage}]")
      }
    }
  }

}
