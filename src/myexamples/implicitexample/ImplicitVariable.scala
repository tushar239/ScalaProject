package myexamples.implicitexample

/**
  * @author Tushar Chokshi @ 12/20/16.
  */
// http://www.cakesolutions.net/teamblogs/demystifying-implicits-and-typeclasses-in-scala
// Remember that implicits are accessed by type and not by name
// So if you keep two implicit variables of the same type and if you try to access one of them implicitly then Scala will get confused and throw an error
object ImplicitVariable {

  implicit val code = 7
  implicit val greeting = "Hello"
  //implicit val name = "John" // If you uncomment this, Scala will get confused to use which String variable as implicit

  // implicit needs be first keyword in the method parameters group
  // so here both greeting and code are expected to be accessed implicitly
  // if you don't want to access any parameter implicitly then put it in different parameter group (e.g. lastName)
  def greet(lastName:String)(implicit greeting:String, code: Int): String ={
    s"$greeting, 00$code"
  }

  def main(args: Array[String]): Unit = {
    println(greet("Chokshi")) // Hello, 007
  }
}
