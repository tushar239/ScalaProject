package myexamples

import java.io.{FileNotFoundException, FileReader, IOException}

/**
  * @author Tushar Chokshi @ 12/20/16.
  */
// https://www.tutorialspoint.com/scala/scala_exception_handling.htm
// there is another way of exception handling using util.Try. You can read about it in MyOwnExample file.
object ExceptionHandlingExample {
  def main(args: Array[String]) {
    try {

      val f = new FileReader("input.txt")

    } catch {

      case ex: FileNotFoundException => {
        println("Missing file exception")
      }

      case ex: IOException => {
        println("IO Exception")
      }

    } finally {

      println("Exiting finally...")

    }
  }
}
