package myexamples

/**
  * @author Tushar Chokshi @ 12/19/16.
  */

/*
trait in Scala is like interface in Java.
The only difference is that trait can have implementing methods also. It is like Java 8's interface.

Here is an example of an abstract class, trait with abstract methods and trait with methods having body.
*/
object TraitExample {

  abstract class Animal {
    def speak
  }

  trait FourLeggedAnimal {
    def walk

    def run
  }

  trait WaggingTail {
    def startTail {
      println("tail started")
    }

    def endTail {
      println("tail ended")
    }
  }

  class Dog extends Animal with WaggingTail with FourLeggedAnimal {
    def speak: Unit = {
      println("Dog says 'woof'")
    }

    override def walk: Unit = {
      println("Dog is walking")
    }

    override def run: Unit = {
      println("Dog is running")
    }
  }

  def main(args: Array[String]): Unit = {
    val dog: Dog = new Dog()

    dog.speak
    dog.walk
    dog.run

  }
}
