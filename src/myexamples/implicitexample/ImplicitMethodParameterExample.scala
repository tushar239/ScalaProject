package myexamples.implicitexample

/**
  * @author Tushar Chokshi @ 12/20/16.
  */
// http://stackoverflow.com/questions/10375633/understanding-implicit-in-scala

// Implicit Parameters:
// The final parameter list on a method can be marked implicit, which means the values will be taken from the context in which they are called.
// If there is no implicit value of the right type in scope, it will not compile.
// Since the implicit value must resolve to a single value and to avoid clashes, it's a good idea to make the type specific to its purpose, e.g. don't require your methods to find an implicit Int!
object ImplicitMethodParameterExample {

  class Prefixer(val prefix: String) {
  }

  // Here, we are saying that 'addPrefix' will use
  def addPrefix(s: String)(implicit p: Prefixer) = p.prefix + s

  def main(args: Array[String]): Unit = {
    implicit val myImplicitPrefixer: Prefixer = new Prefixer("***")
    println(addPrefix("abc")) // ***abc
  }
}
