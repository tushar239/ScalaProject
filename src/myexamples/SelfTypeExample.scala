package myexamples

/**
  * @author Tushar Chokshi @ 12/19/16.
  */
object SelfTypeExample {

  // "self: Person =>" syntax says that if some class implements DormResident trait, then it has to extend Person class also
  // This is also called mixing of types.
  trait DormResident { self: Person =>

  }

  // case class, which is similar to Java's java beans (pojos)
  // The only thing that you can provide to it is constructor parameters.
  // Scala internally creates all required stuff like 'Singleton Object' with apply method, toString method etc.
  case class Person(firstName: String, lastName: String)


  // This is a normal class (not a case class), so you need to take care of creating it Singleton Object, if you need.
  class Student(fN: String, lN: String) extends Person(fN, lN) with DormResident {
    val fullName = s"$fN $lN"

  }

  object Student  {

    def apply(fN: String, lN: String): Student = new Student(fN, lN)

  }

  def main(args: Array[String]): Unit = {
    val person: Person = Person("Tushar", "Chokshi")
    println(person.toString) // Person(Tushar,Chokshi)

    val student: Person = Student("Tushar", "Chokshi")
    println(student.toString) // Person(Tushar,Chokshi)
  }
}
