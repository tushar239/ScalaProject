package myexamples

import scala.collection.immutable.IndexedSeq
import scala.language.postfixOps;
/*
Expression, Statement, Method, Function, Procedure
--------------------------------------------------

Expression returns some value.
Statement does not return any value.

Methods can be Procedures (returning Unit(Void)) or Functions (returns something).
Right hand side of a method is an expression or a statement.

Method is made of Expression or Statement.
e.g.
  def someMethod : Double = {5.0}
Here {5.0} is an Expression.
So someMethod is a Function

  def someMethod : Unit = {println(5.0)}
Here, {println(5.0)} is a statement.
So someMethod is a procedure


Expression does not take parameters, whereas Function takes.

If you assign a name to an expression, it becomes a Function.
e.g. def someName(v : Double) : Double = {5.0 + v}
Here {5.0 + v} is an expression. You assigned a name ‘someName’ to it using ‘def’ and so it became a Function.
You can convert this Function that looks like a Method into really a Function object using
  val v1 : (Double) => Double = someName
  or
  val v1= someName _
Here v1 is a type of (Double) => Double which is an (method input param) => (method output param). It is a type of Function1 trait.

 */
object MyOwnExample {

  def main(args: Array[String]) {

    tryForLoop("Sun")

    var output: Double = tryMethod(10)
    println(output) // 314.0

    tryFunction()

    tryStatementExpressionFunctionProcedure

    tryTuple()

    val tuple: (Int, Int) = tryNestedFunctions()
    println(tuple._1, tuple._2) // (5,10)

    tryTwoTypesOfFunctionCreation()

    tryAssignFunctionToAVariable()
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


  def tryMethod(radius: Double): Double = {
    return 3.14 * radius * radius
  }

  def tryFunction(): Unit = {
    // Below is an example of Function1 that takes one input parameter (Double) and give output (Double)
    val someFunc = (radius: Double) => { 3.14 * radius * radius }: Double
    val andThenFunc = someFunc.andThen((outputFromSomeFunc: Double) => {
      4.1 * outputFromSomeFunc * outputFromSomeFunc
    }: Double)

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

  /*
  Expression and Function are almost the same with little difference.

  Both returns the value and both can be assigned to the variable.

  Expression does not take parameters, whereas Function takes.

  Methods cannot be passed to another method. Functions can be passed to another method/function, as they are actually assigned to a variable and variable can be passed as a parameter to another method/function.

  Procedures are just like methods that do not return anything (or returns Unit)
   */
  def tryStatementExpressionFunctionProcedure: Unit = { // You can say that this method is a Procedure as it does not return anything (or returns Unit)

    // Expression is something that returns a value
    // Here is an example of Expression Block. Expression Block is something that is wrapped by { } and returns some value.
    // Statements are something that does not return any value.
    val exp : String = { // This is an Expression Block because it returns a value
      val firstName: String = "Tushar" // This whole line is a Statement, but right hand side of an assignment is an Expression because it returns a value "Tushar"
      val lastName: String = "Chokshi"
      s"$firstName $lastName" // Returned value from Expression Block
    }
    println(exp) // Tushar Chokshi

    def method(firstName: String, lastName: String): String = {
      s"$firstName $lastName"
    }
    println(method("Tushar", "Chokshi")) // Tushar Chokshi

    // Functions are named Expressions that can take parameters. Here, expression is give a name called func
    // methods cannot be passed to another methods, Functions can be passed because they are assigned to a variable and a variable can be passed to another function/method.
    val func = (firstName: String, lastName: String) => {
      s"$firstName $lastName"
    }: String

    println(func("Tushar", "Chokshi")) // Tushar Chokshi

    // Functions can be passed as a parameter, methods cannot be.
    passMethodReturnValue(method("Tushar", "Chokshi")) // method is invoked and returned value is passed to passMethod method
    passFunction(func) // func object is passed to passFunction method. And function is invoked inside passFunction

    // Procedure is a Named Statement.
    // They are just like methods that do not return anything.
    // You can omit the return type ad Scala will Infer the return type as Unit
    // You can even omit the equals sign (=), but it is considered as a bad practice
    def proc(firstName: String, lastName: String): Unit =
      println(s"$firstName $lastName")
    // def proc(firstName:String, lastName:String) { println(s"$firstName $lastName") }
    proc("Tushar", "Chokshi") // Tushar Chokshi
  }

  def passMethodReturnValue(name: String): String = {
    name
  }

  def passFunction(func: Function2[String, String, String]): String = {
    val result: String = func("Tushar", "Chokshi")
    result
  }

  def tryTuple(): Unit = {
    // Expression returning a Tuple2

    // val tupleReturningExp : Tuple2(Double, Double)
    // same as
    val tupleReturningExp : (Double, Double) = {
      val length:Double = 5.0
      val width:Double = 5.0
      (length, width) // returning tuple from this expression block
    }
    println(tupleReturningExp)// (5.0,5.0)

    // takeTupleAsParameterMethod method takes two parameters. So you can convert any
    //val f1: (Double, Double) => Double = takeTupleAsParameterMethod _

    // passed expression block is executed right away and tupled function gets Tuple2 as an input
    // tupled is a function in Function2 trait. it takes Tuple2 as an input and calls apply method of Function2 with two parameters of Tuple2. apply method will call your method with those two parameters.
    // Later on you will see how to assign a method to a variabl. Here By adding ‘_’ at the end of method name, we can assign a method to a variable and that variable becomes a type of Function2 trait. There are two ways to assign a method to a variabl, where variable becomes a type of Function. You will see both of these ways later on. For now, understand that ‘takeTupleAsParameterMethod _’ returns a variable of type Function’
    val result: Double = (takeTupleAsParameterMethod _).tupled(tupleReturningExp)
    println(result) // 25.0
  }


  def takeTupleAsParameterMethod(param1:Double, param2:Double): Double = {
    param1 * param2
  }

  def tryNestedFunctions() = { // this function has another functions inside it. Return type is inferred by scala. It is Tuple2 here.

    def calculateSize() : Int = {
      5
    }
    def calculateLength() : Int = {
      10
    }
    (calculateSize(), calculateLength()) // returning tuple

  }

  def someFunction() : Double = {
    5.0
  }
  def someFunction1(some : Double) : Double = {
    some
  }

  /*
  Two ways to create a function
  1. Named Function using 'def'
  2. Anonymous Function (Function Literal) using 'val' - Java Style

  Both do the same thing.
  As Anonymous Function is assigned to a variable, that variable can be kept outside the scope of a method and can be used in other methods.

  def outerMethod() = {
    //def f1 // this is not possible
    val f3 // this is possible

    def someMethod() = {
      f3 = (d1:Double, d2:Double) => {d1+d2} : Double
    }
    def someOtherMethod() = {
      f3(5.0, 5.0)
    }
  }
   */
  def tryTwoTypesOfFunctionCreation() = {
    // Named function
    def f1(d1:Double, d2:Double) : Double = {
      d1 + d2
    }
    println(f1(5.0, 5.0)) // 10.0

    // Anonymous function
    val f2 : (Double, Double) => Double = (d1, d2) => d1 + d2;
    println(f2(5.0, 5.0)) // 10.0
    // or
    val f3 = (d1:Double, d2:Double) => {d1+d2} : Double
    println(f3(5.0, 5.0)) // 10.0

    passFunc(f1) // 10.0
    passFunc(f2) // 10.0
    passFunc(f3) // 10.0

    println(returnFunc().apply(5.0, 5.0)) // 10.0
  }
  def passFunc(f : (Double, Double) => Double): Unit = {
    println(f(5.0, 5.0))
  }
  def returnFunc() : (Double, Double) => Double = {
    def f1(d1:Double, d2:Double) : Double = {
      d1 + d2
    }
    f1
  }


  def tryAssignFunctionToAVariable() = {
    val v = someFunction // this is fine because () is implicit after someMethod

    // val v1 = someMethod1 // this won't work because you are not passing expected parameters

    // There are two ways to assign a method a variable (convert a method to a Function)
    // 1. Assign a type to a variable as shown below
    // 2. use _ as shown below
    // By using one of these methods, you can assign a method to a variable that is actually a Lambda (Function) at the end. You can invoke that method by invoking that Function variable.

    // 1.
    // type of a method is a combination of input parameter types and return type ((Double) => Double)
    // To assign a method to a variable, you need to specify the type of that variable as shown below
    // Basically, you can convert a method into a Function by assigning it to a variable like this
    val v1 : (Double) => Double = someFunction1
    println(v1) // myexamples.MyOwnExample$$$Lambda$25/2081303229@48eff760. v1 is a lambda of type Function1 Trait now
    println(v1(5.0)) // 5.0

    // 2.
    val v2 = someFunction1 _
    println(v2) // myexamples.MyOwnExample$$$Lambda$26/1076835071@573f2bb1. v2 is a lambda of type Function1 Trait now
    println(v2(5.0)) // 5.0

    tryPassingAFunctionAsParameter(someFunction1)
  }

  def tryPassingAFunctionAsParameter(v : (Double) => Double) = { // Method needs to be converted into Function, so that it can be passed to another method as a parameter
    v(5.0) // 5.0
  }
}
