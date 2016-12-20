package myexamples.implicitexample

/**
  * @author Tushar Chokshi @ 12/20/16.
  */
// http://www.cakesolutions.net/teamblogs/demystifying-implicits-and-typeclasses-in-scala
object ImplictClass {

  implicit class Agent(code: Int) {
    def getCode = s"000$code"
  }

  def hello(agent: Agent) = s"Hello, ${agent.getCode}"

  def main(args: Array[String]): Unit = {
    hello(7) // Hello, $0007
  }

}
