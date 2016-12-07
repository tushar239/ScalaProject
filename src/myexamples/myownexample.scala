package myexamples

import scala.collection.immutable.IndexedSeq
import scala.language.postfixOps;

object MyOwnExample {

  def main(args: Array[String]) {

    tryForLoop("Sun")

    tryFunction()

    var output:Double = tryMethod(10)
    println(output) // 314.0

  }

  def tryForLoop(day: String): Unit = { // This is a method and not a function because method cannot be assigned to a variable, wherease function can be.
    val days: List[String] = List(
      "Sun",
      "Mon",
      "Tue",
      "Wed",
      "Thu",
      "Fri",
      "Sat") // see in scala, [] is same as <> in java. It is used for generics.

    // Without 'yield', for loop is just like java style for loop that does not return anything. 'yield' allows for loop to be an expression instead of a statement.
    val result: List[String] = for (d <- days) yield {

      // Scala encourages to use 'match', same as 'switch-case' in java, instead of 'if-else'
      // Unlike to java, 'match' can return a value, you can write if condition with 'case' also.
      d match {
        case someDay if (someDay == day) => s"$d found"
        case _ => null //s"$day not found"
      }

    }

    println(result) // result of a for loop is a List containing returned value from each iteration
    //O/P: List(Sun found, null, null, null, null, null, null)

    //for(i <- 0 to result.size - 1)
    // or
    val result2: IndexedSeq[String] = for (i <- 0 until result.size) yield {
      result(i) // see we are not using list.get(i) here. simply list(i) works.
    }
    println(result2) // Vector(Sun found, null, null, null, null, null, null)

    // For loop with if condition
    for (day <- days) {
      if (day == "Sun") println(day);
    }
    // is same as pattern guarded for loop
    for (day <- days if day == "Sun") {
      println(day);
    }

    // nested for loop in scala
    val result3: IndexedSeq[String] = for {
      i <- 0 until 3
      j <- 0 to 2
      day <- days
    } yield {
      s"$i,$j,$day"
    }
    println(result3) // Vector(0,0,Sun, 0,0,Mon, 0,0,Tue, 0,0,Wed, 0,0,Thu, 0,0,Fri, 0,0,Sat, 0,1,Sun, 0,1,Mon, 0,1,Tue, 0,1,Wed, 0,1,Thu, 0,1,Fri, 0,1,Sat, 0,2,Sun, 0,2,Mon, 0,2,Tue, 0,2,Wed, 0,2,Thu, 0,2,Fri, 0,2,Sat, 1,0,Sun, 1,0,Mon, 1,0,Tue, 1,0,Wed, 1,0,Thu, 1,0,Fri, 1,0,Sat, 1,1,Sun, 1,1,Mon, 1,1,Tue, 1,1,Wed, 1,1,Thu, 1,1,Fri, 1,1,Sat, 1,2,Sun, 1,2,Mon, 1,2,Tue, 1,2,Wed, 1,2,Thu, 1,2,Fri, 1,2,Sat, 2,0,Sun, 2,0,Mon, 2,0,Tue, 2,0,Wed, 2,0,Thu, 2,0,Fri, 2,0,Sat, 2,1,Sun, 2,1,Mon, 2,1,Tue, 2,1,Wed, 2,1,Thu, 2,1,Fri, 2,1,Sat, 2,2,Sun, 2,2,Mon, 2,2,Tue, 2,2,Wed, 2,2,Thu, 2,2,Fri, 2,2,Sat)
  }

  def tryFunction() : Unit = {
    // Below is an example of Function1 that takes one input parameter (Double) and give output (Double)
    val someFunc = (radius:Double) => {3.14 * radius * radius} : Double
    val andThenFunc = someFunc.andThen( (outputFromSomeFunc:Double) => {4.1 * outputFromSomeFunc * outputFromSomeFunc}:Double)

    // same as below java 8 function
    // Function<Double, Double> javaFunc = (rad) -> 3.14 * rad * rad;
    println(someFunc) // udemy.MyOwnExample$$$Lambda$19/853993923@5056dfcb

    println(someFunc.apply(10)) // 314.0
    // or
    println(someFunc(10)) // 314.0


    println(andThenFunc.apply(10)) // 404243.6
    // or
    println(andThenFunc(10)) // 404243.6

  }

  def tryMethod(radius : Double) : Double = {
    return 3.14 * radius * radius
  }
}
