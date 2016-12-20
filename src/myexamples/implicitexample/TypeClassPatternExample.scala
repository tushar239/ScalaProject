package myexamples.implicitexample

/**
  * @author Tushar Chokshi @ 12/20/16.
  */

// http://typelevel.org/cats/typeclasses.html

// https://gist.github.com/davegurnell/a614c67e8d52c113d36d
// The parts of the type class pattern are:
//
//   1. the "type class" itself -- a trait with a single type parameter;
//
//   2. type class "instances" for each type we care about, each marked with the `implicit` keyword;
//
//   3. an "interface" to the type class -- one or more methods with `implicit` parameter lists.
object TypeClassPatternExample {

  // Type Class
  trait Show[A] {
    def show(f: A): String
  }


  // Type Class instance
  implicit val stringShow = new Show[String] {
    def show(s: String) = s
  }
  // Type Class instance
  implicit val intShow = new Show[Int] {
    def show(i: Int) = "" + i
  }

  case class Person(val firstName: String, val lastName: String)
  // Type Class instance
  implicit val personShow = new Show[Person] {
    def show(person: Person) = person.toString
  }

  // metho that is an interface to Type Class
  // def log[A](a: A)(implicit showObj: Show[A]) = println(showObj.show(a))
  // is same as
  def log[A: Show](a: A) = println(implicitly[Show[A]].show(a))

  def main(args: Array[String]): Unit = {
    // See, scala automatically finds a correct Type class instance to be passed to log method
    log("some string") // some string
    log(1) // 1
    log(Person("Tushar", "Chokshi")) // Person(Tushar,Chokshi)
  }

}
