package myexamples.covariance

/**
  * @author Tushar Chokshi @ 12/19/16.
  */
// https://blog.codecentric.de/en/2015/03/scala-type-system-parameterized-types-variances-part-1/
object CovarianceExample2 {

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


  }

}
