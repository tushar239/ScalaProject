package myexamples.implicitexample

/**
  * @author Tushar Chokshi @ 12/20/16.
  */

// http://www.cakesolutions.net/teamblogs/demystifying-implicits-and-typeclasses-in-scala
// Oh, and there's another wrinkle: implicit conversions. If you define a one argument method with the implicit modifier, Scala uses that as a way to convert arguments of the input type to the output type in calls when this method is in scope.
object ImplicitMethod {

  implicit def agentCodename(i: Int) = s"00$i"

  def hello(name: String) = s"Hello, $name!"

  def main(args: Array[String]): Unit = {
    hello(7) // Hello, 007!
  }
}
