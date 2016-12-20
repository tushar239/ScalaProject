package myexamples.covariance

/**
  * @author Tushar Chokshi @ 12/19/16.
  */
// https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
object CovarianceExample {

  abstract class Fruit {
    def name: String
  }

  class Orange extends Fruit {
    def name = "Orange"
  }

  class Apple extends Fruit {
    def name = "Apple"
  }

  // F means F is a invariant parameter type. So, instance of CovariantBox[Apple] CAN NOT be assigned to CovariantBox[Fruit] and vice-versa
  class InvariantBox[F <: Fruit](aFruit: F) {

    def fruit: F = aFruit

    def contains(aFruit: Fruit) = fruit.name == aFruit.name
  }


  // +F means F is a covariant parameter type. So, instance of CovariantBox[Apple] can be assigned to CovariantBox[Fruit]
  class CovariantBox[+F <: Fruit](aFruit: F) {

    def fruit: F = aFruit

    def contains(aFruit: Fruit) = fruit.name == aFruit.name
  }

  // -F means F is a contravariant parameter type. So, instance of ContravariantBox[Fruit] can be assigned to ContravariantBox[Apple]
  /*class ContravariantBox[-F <: Fruit](aFruit: F) {

    def fruit: F = aFruit

    def contains(aFruit: Fruit) = fruit.name == aFruit.name
  }*/


  def main(args: Array[String]): Unit = {
    {
      var appleBox = new InvariantBox[Apple](new Apple)
      var orangeBox = new InvariantBox[Orange](new Orange)

      // Illegal: Box[Apple] is no subtype of Box[Fruit].
      //var box: Box[Fruit] = new Box[Apple](new Apple)
    }

    {
      var appleBox = new CovariantBox[Apple](new Apple)
      // Now this is possible because of covariant Box1[+F]
      var box: CovariantBox[Fruit] = appleBox
    }

/*    {
      val stackOfAny: Stack[Any] = new Stack()
      val push: Stack[Any] = stackOfAny.push("hello")

      var s: Stack[Any] = new Stack().push("hello")
      s = s.push(new Object())
      s = s.push(7)
      println(s)
    }*/

  }
/*
  class Stack[+A] {

    def push[B >: A](elem: B): Stack[B] = new Stack[B] {
      override def top: B = elem

      override def pop: Stack[B] = Stack.this

      override def toString() = elem.toString() + " " + Stack.this.toString()
    }

    def top: A = sys.error("no element on stack")

    def pop: Stack[A] = sys.error("no element on stack")

    override def toString() = ""
  }*/

}
