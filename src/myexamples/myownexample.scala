package myexamples

import scala.collection.immutable.IndexedSeq
import scala.collection.mutable
import scala.language.postfixOps
import scala.runtime.Nothing$
import scala.util.{Failure, Success, Try};

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


Usage of wildcard _ (underscore)

1. Pattern Matching (for default case of 'match' expression)
    val days: List[String] = List("Sun","Mon","Tue","Wed","Thu","Fri","Sat")

    val result: List[String] = for (d <- days) yield {

      // Scala encourages to use 'match', same as 'switch-case' in java, instead of 'if-else'
      // Unlike to java, 'match' can return a value, you can write if condition with 'case' also.
      d match {
        case someDay if (someDay == day) => s"$d found"
        case _ => null //s"$day not found"
      }

    }

2. Assigning a function to a variable

  def someFunction1(some: Double): Double = {
      some
  }
  val v1: (Double) => Double = someFunction1
  // or
  val v2 = someFunction1 _

3. Assigning tuple members to individual variables

  val tupleOf4: (String, String, Int, String) = ("Tushar", "Chokshi", 35, "Married")

  // Extracting Tuple members

  // Option 1
  val firstName = tupleOf4._1
  val lastName = tupleOf4._2
  val maritalStatus = tupleOf4._4

  // Option 2
  // Here, I don't want to use age from tupleOf4, so I can decide not to assign it to any variable.
  // I am just extracting firstName, lastName and maritalStatus from a tuple

  val (firstName, lastName, _, maritalStatus) = tupleOf4

  println(firstName) // Tushar
  println(lastName) // Chokshi
  println(maritalStatus) // Married

4. Passing function to higher order function in Shorter Way

    def isVIP(firstName: String, lastName: String,
              isHighStatus: (String, String) => Boolean): Boolean = {
      isHighStatus(firstName, lastName)
    }

    // Option 1
    def vipDecider(firstName: String, lastName: String): Boolean = {
      firstName == "Tushar" && lastName == "Chokshi"
    }
    println(isVIP("Tushar", "Chokshi", vipDecider)) // true

    // Option 2
    isVIP("Tushar", "Chokshi", (fN, lN) => fN == "Tushar" && lN == "Chokshi"))

    // Option 3  (Shorter way)
    // Here, you need to be very careful because _s (underscores) used in 3rd parameter are replaced by values passed before it exactly in the same order.
    // e.g. first _ is replaced by "Tushar" and second _ is replaced by "Chokshi"
    // If you do any mistake in passing input parameters, then it can result in wrong evaluation of the function (3rd parameter)
    isVIP("Tushar", "Chokshi", _ == "Tushar" && _ == "Chokshi")



   val days: List[String] = List("Sun","Mon","Tue","Wed","Thu","Fri","Sat")

   // Option 1
   days.foreach(day -> println(day))

   // Option 2
   days.foreach(println(_))


5. Like Method Reference in Java

   class C(fn:String) {
    val firstName:String = fn

    def method() : String ={ "hi "+firstName }
   }

   List listOfCInstances : List[C] = {new C("Tushar"), new C("Miral"), new C("Srikant")}
   listOfCInstances.map(c -> c.method())
   //or
   listOfCInstances.map(_.method())


 */
object MyOwnExample {


  def main(args: Array[String]) {

    tryForLoop("Sun")

    var output: Double = tryMethod(10)
    println(output) // 314.0

    tryFunction()

    tryStatementExpressionFunctionProcedure

    tryTupledMethodOfFunction()

    val tuple: (Int, Int) = tryNestedFunctions()
    println(tuple._1, tuple._2) // (5,10)

    tryTwoTypesOfFunctionCreation()

    tryAssignFunctionToAVariable()

    tryPartiallyAppliedFunction_eg1()
    tryPartiallyAppliedFunction_eg2()

    tryCurriedFunction()

    tryByNameParameter()

    tryClosure()

    tryTuple()

    tryCollection()

    tryHigherOrderFunctionsOnList()

    tryMapAndSet()

    tryImmutableCollection()

    tryArrays()

    tryOptionAndUtilTry()

    tryOperatorOverloading()
  }

  def tryForLoop(day: String): Unit = {
    // This is a method and not a function because method cannot be assigned to a variable, wherease function can be.
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
    val someFunc = (radius: Double) => {
      3.14 * radius * radius
    }: Double
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
  def tryStatementExpressionFunctionProcedure: Unit = {
    // You can say that this method is a Procedure as it does not return anything (or returns Unit)

    // Expression is something that returns a value
    // Here is an example of Expression Block. Expression Block is something that is wrapped by { } and returns some value.
    // Statements are something that does not return any value.
    val exp: String = {
      // This is an Expression Block because it returns a value
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
    passFunctionAsParameter(func) // func object is passed to passFunction method. And function is invoked inside passFunction

    def passMethodReturnValue(name: String): String = {
      name
    }

    def passFunctionAsParameter(func: Function2[String, String, String]): String = {
      val result: String = func("Tushar", "Chokshi")
      result
    }

    // Procedure is a Named Statement.
    // They are just like methods that do not return anything.
    // You can omit the return type ad Scala will Infer the return type as Unit
    // You can even omit the equals sign (=), but it is considered as a bad practice
    def proc(firstName: String, lastName: String): Unit =
    println(s"$firstName $lastName")
    // def proc(firstName:String, lastName:String) { println(s"$firstName $lastName") }
    proc("Tushar", "Chokshi") // Tushar Chokshi


  }

  def tryTupledMethodOfFunction(): Unit = {
    // Expression returning a Tuple2

    // val tupleReturningExp : Tuple2(Double, Double)
    // same as
    val tupleReturningExp: (Double, Double) = {
      val length: Double = 5.0
      val width: Double = 5.0
      (length, width) // returning tuple from this expression block
    }
    println(tupleReturningExp) // (5.0,5.0)

    // takeTupleAsParameterMethod method takes two parameters. So you can convert any
    //val f1: (Double, Double) => Double = takeTupleAsParameterMethod _

    // passed expression block is executed right away and tupled function gets Tuple2 as an input
    // tupled is a function in Function2 trait. it takes Tuple2 as an input and calls apply method of Function2 with two parameters of Tuple2. apply method will call your method with those two parameters.
    // Later on you will see how to assign a method to a variabl. Here By adding ‘_’ at the end of method name, we can assign a method to a variable and that variable becomes a type of Function2 trait. There are two ways to assign a method to a variabl, where variable becomes a type of Function. You will see both of these ways later on. For now, understand that ‘takeTupleAsParameterMethod _’ returns a variable of type Function’
    val result: Double = (takeTupleAsParameterMethod _).tupled(tupleReturningExp)
    println(result) // 25.0

  }

  def takeTupleAsParameterMethod(param1: Double, param2: Double): Double = {
    param1 * param2
  }


  def tryNestedFunctions() = {
    // this function has another functions inside it. Return type is inferred by scala. It is Tuple2 here.

    def calculateSize(): Int = {
      5
    }
    def calculateLength(): Int = {
      10
    }
    (calculateSize(), calculateLength()) // returning tuple

  }


  /*
  Two ways to create a function
  1. Named Function using 'def'
  2. Anonymous Function (Function Literal or Lambda) using 'val' - Java Style

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
    def f1(d1: Double, d2: Double): Double = {
      d1 + d2
    }
    println(f1(5.0, 5.0)) // 10.0

    // Anonymous function
    val f2: (Double, Double) => Double = (d1, d2) => d1 + d2;
    println(f2(5.0, 5.0)) // 10.0
    // or
    val f3 = (d1: Double, d2: Double) => {
      d1 + d2
    }: Double
    println(f3(5.0, 5.0)) // 10.0

    passFunc(f1) // 10.0
    passFunc(f2) // 10.0
    passFunc(f3) // 10.0

    println(returnFunc().apply(5.0, 5.0)) // 10.0


    def passFunc(f: (Double, Double) => Double): Unit = {
      println(f(5.0, 5.0))
    }
    def returnFunc(): (Double, Double) => Double = {
      def f1(d1: Double, d2: Double): Double = {
        d1 + d2
      }
      f1
    }

  }


  def tryAssignFunctionToAVariable() = {
    def someFunction(): Double = {
      5.0
    }
    val v = someFunction // this is fine because () is implicit after someMethod

    def someFunction1(some: Double): Double = {
      some
    }
    // val v1 = someMethod1 // this won't work because you are not passing expected parameters

    // There are two ways to assign a Named Function (function with 'def') to a variable
    // 1. Assign a type to a variable as shown below
    // 2. use _ as shown below
    // By using one of these approaches, you can assign a method to a variable that is actually a Lambda (Function object) at the end. You can invoke Function's methods using that variable now.

    // 1.
    // type of a method is a combination of input parameter types and return type ((Double) => Double)
    // To assign a method to a variable, you need to specify the type of that variable as shown below
    // Basically, you can convert a method into a Function by assigning it to a variable like this
    val v1: (Double) => Double = someFunction1
    println(v1) // myexamples.MyOwnExample$$$Lambda$25/2081303229@48eff760. v1 is a lambda of type Function1 Trait now
    println(v1(5.0)) // 5.0

    // 2.
    val v2 = someFunction1 _
    println(v2) // myexamples.MyOwnExample$$$Lambda$26/1076835071@573f2bb1. v2 is a lambda of type Function1 Trait now
    println(v2(5.0)) // 5.0
  }


  def tryPassingAFunctionAsParameterToAnotherFunction() = {
    // There are 3 ways to pass a function as a parameter to another function
    // Udemy video shows only first 2 ways. 3rd way is more like Java 8 style.

    // 1. Longer Way
    def someFunction1(some: Double): Double = {
      some
    }
    passFunctionAsParameter(5.0, someFunction1)

    def vipDecider(firstName: String, lastName: String): Boolean = {
      firstName == "Tushar" && lastName == "Chokshi"
    }
    println(isVIP("Tushar", "Chokshi", vipDecider)) // true

    // 2. Shorter Way
    // Here, you need to be very careful because _s (underscores) used in 3rd parameter are replaced by values passed before it exactly in the same order.
    // e.g. first _ is replaced by "Tushar" and second _ is replaced by "Chokshi"
    // If you do any mistake in passing input parameters, then it can result in wrong evaluation of the function (3rd parameter)
    println(isVIP("Tushar", "Chokshi", _ == "Tushar" && _ == "Chokshi")) // true

    // 3. Java style Way
    println(isVIP("Tushar", "Chokshi", (fN, lN) => fN == "Tushar" && lN == "Chokshi")) // true
    println(isVIP2("Tushar", "Chokshi", (fN, lN) => fN == "Tushar" && lN == "Chokshi")) // true

    def passFunctionAsParameter(input: Double,
                                func: (Double) => Double) = {
      // Method needs to be converted into Function, so that it can be passed to another method as a parameter
      func.apply(input)
    }

    def isVIP(firstName: String, lastName: String,
              isHighStatus: (String, String) => Boolean): Boolean = {
      isHighStatus(firstName, lastName)
    }
    def isVIP2(firstName: String, lastName: String,
               // Java Style
               isHighStatus: Function2[String, String, Boolean]): Boolean = {
      isHighStatus(firstName, lastName)
    }
  }

  def tryPartiallyAppliedFunction_eg1(): Unit = {

    def vipDecider(firstName: String, lastName: String): Boolean = {
      firstName == "Tushar" && lastName == "Chokshi"
    }


    val fN = "Tushar"

    // To evaluate isHighStatus function, you need two input parameters, but you have only one
    // so 'isVIP_Partial' is a a partial function.
    // it has to return another function that is capable of taking another input parameter to get the final result.
    def isVIP_Partial(firstName: String, isHighStatus: (String, String) => Boolean)
    : (String) => Boolean = {
      return isHighStatus(firstName, _: String) // by providing '_', you tell scala that you don't have second parameter value available at present to evaluate 'isHighStatus' fully. This will force you to return a Function that takes remaining parameter(s) as input.
    }

    val finalFunction: (String) => Boolean
    = isVIP_Partial(fN, vipDecider)


    val lN = "Chokshi"
    val result: Boolean = finalFunction.apply(lN)

    println(result) // true

  }

  def tryPartiallyAppliedFunction_eg2() = {
    def compareStrings(s1: String, s2: String): Boolean = {

      val result: Boolean =
        s1 match {
          case str1 if (str1.compareTo(s2) == 0) => true
          case _ => false
        }
      result

    }

    // Full function
    def compare_full(s1: String, s2: String, compareStrings: (String, String) => Boolean)
    : Boolean = {
      compareStrings(s1, s2)
    }
    // Applying Full Function fully
    val result1: Boolean = compare_full("abc", "abc", compareStrings) // this facility is available in Java 8
    println(result1) // true

    // Applying Full Function partially
    val compare_full1: (String) => Boolean = compare_full(_: String, "abc", compareStrings) // this facility is NOT available in Java 8
    val result2: Boolean = compare_full1.apply("abc")
    println(result2) // true

    // Partial Function
    def compare_partial1(s2: String, compareStrings: (String, String) => Boolean) // this facility is available in Java 8
    : (String) => Boolean = {
      (s1) => compareStrings(s1, s2)
    }
    // Applying Partial Function partially
    val partialResult: (String) => Boolean = compare_partial1("abc", compareStrings)
    val result3: Boolean = partialResult.apply("abc")
    println(result3) // true

  }

  /*
  What is currying?
  -----------------
  f(3, 4, 5) = 3 * 4 + 5 = 17
  we may apply the arguments 3, 4, 5 at the same time and get:

  But we may also apply only 3 and get:
  f(3, y, z) = g(y, z) = 3 * y + z

  We have now a new function g, taking only two arguments. We can curry again this function, applying 4 to y:
  g(4, z) = h(z) = 3 * 4 + z

  Currying allows to turn a function that expects two arguments into a function that expects only one, and that function returns a function that expects the second argument.
  Creating basically a chain of functions.

  You can apply unCurried function partially or fully.
  But Curried function forces you to apply it partially.

  http://www.codecommit.com/blog/scala/function-currying-in-scala
  In computer science, currying, invented by Moses Schönfinkel and Gottlob Frege, is the technique of transforming a function that takes multiple arguments into a function that takes a single argument (the other arguments having been specified by the curry).

  // Not curried
  def add(x:Int, y:Int) = x + y

  add(1, 2) // 3

  // Curried
  def add(x:Int) = (y:Int) => x + y

  val partialResult : (Int)=>Int = add(1)
  val result:Int = partialResult(2)
  // or simple way
  add(1)(2)

  In the first sample, the add method takes two parameters and returns the result of adding the two.
  The second sample redefines the add method so that it takes only a single Int as a parameter and returns a functional (closure) as a result.  Our driver code then calls this functional, passing the second “parameter”.  This functional computes the value and returns the final result.

  Why Currying is important?
  --------------------------
  https://dzone.com/articles/understanding-currying-scala

  Answer: Code reuse
  With the same value of x, you can use different values of y.
    partialResult(3)
    partialResult(4)
    partialResult(5)
    etc

  What is Parameter Grouping?
  It is just a syntactic sugar. Nothing so great about it.
  It just gives you a freedom to specify logical input parameters in groups.

  def add(x:Int, y:Int) = x + y
  can be written as

  def add(x:Int)(y:Int) = x + y

  */
  def tryCurriedFunction() = {
    def compareStrings(s1: String, s2: String): Boolean = {

      val result: Boolean =
        s1 match {
          case str1 if (str1.compareTo(s2) == 0) => true
          case _ => false
        }
      result

    }

    // Parameter Grouping
    // It is just a syntactic sugar. Nothing so great about it.
    // It just gives you a freedom to specify logical input parameters in groups
    def unCurriedCompare(s1: String, s2: String)
                        (compareStrings: (String, String) => Boolean)
    : Boolean = {
      compareStrings(s1, s2)
    }

    unCurriedCompare("abc", "abc")(compareStrings)

    //  ......... Applying a function partially without Currying ............
    // Java 8 doesn't have this facility

    // Not passing input parameters required for compareStrings, but passing compareStrings function
    val partialResult1: (String, String) => Boolean
    = unCurriedCompare(_: String, _: String)(compareStrings)

    val result1: Boolean = partialResult1.apply("abc", "abc")
    println(result1) // true

    // Passing only one input parameter required by compareStrings
    val partialResult2: (String) => Boolean
    = unCurriedCompare("abc", _: String)(compareStrings)

    val result2: Boolean = partialResult2.apply("abc")
    println(result2) // true


    // Passing only one input parameter required by compareStrings and not passing compareStrings
    val partialResult3: (String, (String, String) => Boolean) => Boolean
    = unCurriedCompare("abc", _: String)(_: (String, String) => Boolean)

    val result3: Boolean = partialResult3.apply("abc", compareStrings)
    println(result3) // true

    // Passing input parameters required for compareStrings, but not passing compareStrings function
    val partialResult4: ((String, String) => Boolean) => Boolean
    = unCurriedCompare("abc", "abc")(_: (String, String) => Boolean)

    val result4: Boolean = partialResult4.apply(compareStrings)
    println(result4) // true


    //  ...... Applying curriedCompare partially.....
    // In fact, Curried function forces you to apply it partially
    // Java 8 has this facility

    // This is a Curried Function
    // Function is Curried, if it forces you to apply it partially
    def curriedCompare[T1](compareStrings: (T1, T1) => Boolean)
    : (T1, T1) => Boolean = {
      compareStrings
    }

    //  ......... Applying a curried function partially ............
    val partialResult5: (String, String) => Boolean
    = curriedCompare(compareStrings)

    // now partialResult5 can be reused for different input strings
    val result5: Boolean = partialResult5.apply("abc", "abc")
    println(result5) // true

    val result6: Boolean = partialResult5.apply("abc", "def")
    println(result6) // false


    // Simple way of calling a curried function
    curriedCompare(compareStrings)("abc", "abc")

  }

  def tryByNameParameter() = {
    // see 'Scala self made document from Udemy.docs'
  }

  def tryClosure(): Unit = {
    // see 'Scala self made document from Udemy.docx'
  }

  // There are Tuple1 to Tuple22 final classes in Scala
  def tryTuple() = {

    val tupleOf2: (String, String) = ("Tushar", "Chokshi")

    // Special way to denote Tuple of 2 elements like key-value pair
    val tupleOf2_diff_way: (String, String) = "Tushar" -> "Chokshi"
    println(tupleOf2_diff_way) // (Tushar,Chokshi)

    val tupleOf4: (String, String, Int, String) = ("Tushar", "Chokshi", 35, "Married")
    println(tupleOf4) // (Tushar,Chokshi,35,Married)

    // Accessing Tuple
    println(tupleOf4._1) // Tushar
    println(tupleOf4._2) // Chokshi
    println(tupleOf4._3) // 35
    println(tupleOf4._4) // Married

    // Accessing Tuple elements using variables
    // If you don't care about some elements, simply specify the placeholder using wildcard _ (underscore)
    val (firstName, lastName, _, maritalStatus) = tupleOf4
    println(firstName) // Tushar
    println(lastName) // Chokshi
    println(maritalStatus) // Married

    // Iterating over Tuple
    tupleOf4.productIterator.foreach(element => println(s"hi $element"))
    // O/P:
    // hi Tushar
    // hi Chokshi
    // hi 35
    // hi Married

    val numberOfElements: Int = tupleOf4.productArity
    println(numberOfElements) // 4

  }

  def tryCollection() = {
    // using cons (Constructor) operator ::
    val list1 = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil

    // using constructor
    // no need to use 'new' operator because List is an object of scala.collection.immutable.List.
    // This is how List object is initialized
    // val List = scala.collection.immutable.List
    // somehow it calls immutable.List's apply method that takes varargs and creates a list from it.
    val list2 = List("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")

    val list3 = List("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")

    // concatenating two lists
    // using ::: operator
    val concatenated1 = list2 ::: list3
    println(concatenated1) // List(Sun, Mon, Tue, Wed, Thu, Fri, Sat, Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)

    // using ++ operator
    val concatenated2 = list2 ++ list3
    println(concatenated2) // List(Sun, Mon, Tue, Wed, Thu, Fri, Sat, Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)

    // zipping two lists
    val days = List("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "something_else1", "something_else2")
    val numbers = List(1, 2, 3, 4, 5, 6, 7)
    val listsZippedIntoTuple: List[(String, Int)] = days.zip(numbers)
    println(listsZippedIntoTuple) // List((Sun,1), (Mon,2), (Tue,3), (Wed,4), (Thu,5), (Fri,6), (Sat,7))

    val all: List[(String, Int)] = days.zipAll(numbers, "hi", 1)
    println(all) // List((Sun,1), (Mon,2), (Tue,3), (Wed,4), (Thu,5), (Fri,6), (Sat,7), (something_else1,1), (something_else2,1))

    // flattening list of lists into one list
    val listOfLists: List[List[String]] = List(List("a", "b", "c"), List("d", "e", "f"))
    val flattenedList: List[String] = listOfLists.flatten
    println(flattenedList) // List(a, b, c, d, e, f)

    // .head and .tail
    println(flattenedList.head) // a
    println(flattenedList.tail) // List(b, c, d, e, f)

    // .size and .reverse
    println(flattenedList.size) // 5
    println(flattenedList.reverse) // List(f, e, d, c, b, a)

    // .contains
    println(flattenedList.contains("c")) // true

    // you can use any list method in two ways
    // 1. dot notation
    println(flattenedList.contains("c")) // true
    // 2. operator notation
    println(flattenedList contains "c") // true

    // traditional for loop using value binding (every element of a list is bound to variable c one by one)
    for (c <- flattenedList) {
      println(c)
    }

    // while loop - It's a bit clunkier as needs to be terminated correctly
    var flattenedListVar = flattenedList;
    while (!flattenedListVar.isEmpty) {
      // is same as flattenedListVar != Nil
      println(flattenedListVar.head)
      flattenedListVar = flattenedListVar.tail
    }

    // .distinct
    val listWithDuplicates = List("a", "b", "a", "c")
    println(listWithDuplicates.distinct) // List(a, b, c)

    // .drop
    println(listWithDuplicates.drop(2)) // List(a, c)

    // .slice - same as subList
    println(listWithDuplicates.slice(1, 3)) // List(b, a)

    // .splitAt - splits a list into two lists
    println(listWithDuplicates.splitAt(1)) // List(List(a), List(b, a, c))

    // .take
    println(listWithDuplicates.take(3)) // List(a, b, a)

    // .sum
    val list5 = List(1, 2, 3, 4, 5)
    println(list5.sum) // 15
  }

  def tryHigherOrderFunctionsOnList(): Unit = {
    // forEach takes a procedure as a parameter. You can think of procedure as Java's Consumer that doesn't return anything.
    // So, forEach is a statement, not an expression
    tryForEach

    // map takes a function as a parameter. I applies that function on each element and returns back the list.
    // so map is a function, not a procedure
    tryMap

    // filter takes a function that returns boolean. It is also called a predicate.
    tryFilter

    // just like filter, partition also takes a predicate, but it return a Tuple having two lists, one that satisfies the predicate and another that doesn't.
    tryPartition

    trySortBy

    // reduce function is applied to two adjacent elements throughout the list and produces one element.
    // scan is a type of reduce function, but it produces an element from each operation of two elements (so a list).
    tryScanFoldReduceEndsWithStartsWithForall_ReductionFunctions
  }

  def tryForEach: Unit = {
    val weekDays = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil

    weekDays.foreach(weekDay => println(weekDay)) // it can take Function Literal

    // OR
    weekDays.foreach(println(_)) //  It can simply do like this. _ takes elements one by one

    // OR
    val func = (weekDay: Any) => println(weekDay): Unit

    weekDays.foreach(func) // it can take Function Object
  }

  def tryMap: Unit = {

    val weekDays = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil
    val newList: List[String] = weekDays.map(weekDay => "my " + weekDay) // it can take Function literal
    println(newList) // List(my Sun, my Mon, my Tue, my Wed, my Thu, my Fri, my Sat)

    // or
    val newList1: List[String] = weekDays.map("my " + _) // you can use _ to access an element of a list just like forEach
    println(newList1) // List(my Sun, my Mon, my Tue, my Wed, my Thu, my Fri, my Sat)

    // or
    val func = (weekDay: Any) => "my " + weekDay: String

    val newList2: List[String] = weekDays.map(func) // it can take Function Object
    println(newList2) // List(my Sun, my Mon, my Tue, my Wed, my Thu, my Fri, my Sat)


    val newList3: List[Boolean] = weekDays.map(_ == "Sun")
    println(newList3) // List(true, false, false, false, false, false, false)

  }

  def tryFilter = {
    val weekDays = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil

    val filteredList: List[String] = weekDays.filter(weekDay => weekDay != "Mon")
    println(filteredList) // List(Sun, Tue, Wed, Thu, Fri, Sat)

    val filteredList1: List[String] = weekDays.filter(_ != "Mon")
    println(filteredList1) // List(Sun, Tue, Wed, Thu, Fri, Sat)

    val predicate = (weekDay: String) => weekDay != "Mon": Boolean
    val filteredList2: List[String] = weekDays.filter(predicate)
    println(filteredList2) // List(Sun, Tue, Wed, Thu, Fri, Sat)
  }

  def tryPartition = {
    val weekDays = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil

    val partitionedTuple: (List[String], List[String]) = weekDays.partition(_ != "Mon")
    println(partitionedTuple) // (List(Sun, Tue, Wed, Thu, Fri, Sat), List(Mon))
  }

  def trySortBy = {
    val weekDays = "Sun" :: "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil

    val sorted: List[String] = weekDays.sortBy(weekDay => weekDay)
    println(sorted) // List(Fri, Mon, Sat, Sun, Thu, Tue, Wed)

    val sorted1: List[String] = weekDays.sortBy(_ (0)) // sort string based on strings' 0th character
    println(sorted1) // List(Fri, Mon, Sat, Sun, Thu, Tue, Wed)

    val sorted2: List[String] = weekDays.sortBy(_ (1)) // sort string based on strings' 1st character
    println(sorted2) // List(Sat, Wed, Thu, Mon, Fri, Sun, Tue)

    // .sorted, .sortWith, .sortBy
    println(weekDays sorted) // List(Fri, Mon, Sat, Sun, Thu, Tue, Wed)
    println(weekDays sorted (Ordering.String.reverse)) // List(Wed, Tue, Thu, Sun, Sat, Mon, Fri)

    val list4 = List("abc", "a", "ab", "ac")
    println(list4.sortWith((x, y) => x.length() < y.length())) // List(a, ab, ac, abc)
    println(list4.sortBy((x) => x.substring(1))) // List(a, ab, abc, ac)

  }

  def tryScanFoldReduceEndsWithStartsWithForall_ReductionFunctions = {

    val someNumbers = List(10, 20, 30, 40, 50, 60)

    tryScan
    tryFold
    tryReduce
    tryStartsWithEndsWithForAll

    def tryScan = {
      // scanLeft is left-associative.
      // scanRight is right-associative.
      // scan method doesn't guarantee the direction. It is merely coincidence that it matches scanLeft. The output depends on the implementation in the traversable (here List)

      // scan function allows parameter grouping. first group takes only identity value and second group takes a function that needs to be applied on two elements.
      val scanResult1: List[Int] = someNumbers.scan(0)((a, b) => a + b)
      println(scanResult1) // List(0, 10, 30, 60, 100, 150, 210)
      // or
      val scanResult2: List[Int] = someNumbers.scan(0)(_ + _)
      println(scanResult2) // List(0, 10, 30, 60, 100, 150, 210)

      val scanResult3: List[Int] = someNumbers.scanLeft(0)(_ + _)
      println(scanResult3) // List(0, 10, 30, 60, 100, 150, 210)

      val scanResult4: List[Int] = someNumbers.scanRight(0)(_ + _)
      println(scanResult4) // List(210, 200, 180, 150, 110, 60, 0)

    }

    def tryFold = {

      // fold is same as scan.
      // Difference is that fold returns the result of all elements.
      val foldResult1: Int = someNumbers.fold(0)((a, b) => a + b)
      println(foldResult1) // 210
    }

    def tryReduce = {
      // reduce is same as fold.
      // Difference is that reduce doesn't take initial value (identity value).
      val reduceResult1: Int = someNumbers.reduce((a, b) => a + b)
      println(reduceResult1) // 210
    }

    def tryStartsWithEndsWithForAll = {
      val weekDays = "Mon" :: "Tue" :: "Wed" :: "Thu" :: "Fri" :: "Sat" :: Nil
      val weekEnds = "Sat" :: "Sun" :: Nil
      val allDays = weekDays ++ weekEnds

      println(allDays startsWith weekDays) // true. see here we have used operator 'startsWith' and not a method '.startsWith'. You can use a method also.
      println(allDays endsWith weekEnds) // true
      println(allDays forall (_ != "monday")) // true
    }
  }

  // For side-effect free code, List,Set,Map are immutable collections. So, they don’t have add,set,remove,put etc methods. The only way to modify is to transform them into a new collection.
  def tryMapAndSet() = {

    // Creating a Map
    val stateCodes: Map[String, String] =
    Map(("California", "CA"),
      ("New York", "NY"),
      ("Vermont", "VT"))

    // or
    // tuple can also be defined as below
    val stateCodes1: Map[String, String] =
    Map(("California" -> "CA"),
      ("New York" -> "NY"),
      ("Vermont" -> "VT"))


    val sb: StringBuilder = new StringBuilder()
    stateCodes.addString(sb)
    println(sb) // California -> CANew York -> NYVermont -> VT

    println(stateCodes1) // Map(California -> CA, New York -> NY, Vermont -> VT)

    // Looking up in a Map
    println(stateCodes("California")) // CA

    // println(stateCodes("Georgia")) // exception for non-existent key

    // stateCodes.get("Georgia") will return None
    // stateCodes("Georigia") will throw NoSuchElementException


    // check for presence of a key
    println(stateCodes.contains("Georgia")) // false

    // All the reduction functions are same as List
    // Only difference is that you need to remember to apply that function on both key and value
    stateCodes.foreach(tuple => println(tuple._1 + "=" + tuple._2))

    // convert Lists to Map
    val states = List("California", "New York", "Vermont")
    val codes = List("Ca", "NY", "VT")
    // zip creates a list of tuples from two lists. To convert that list of Tuple to a map, use toMap
    val mapFromLists: Map[String, String] = states.zip(codes).toMap
    println(mapFromLists) // Map(California -> Ca, New York -> NY, Vermont -> VT)

    // convert map to list
    val keysToList: List[String] = stateCodes.keys.toList
    // or
    // val keysToList: List[String] = stateCodes.keySet.toList
    println(keysToList) // List(California, New York, Vermont)

    val valuesToList: List[String] = stateCodes.values.toList
    println(valuesToList) // List(CA, NY, VT)

    val valuesToArray: Array[String] = stateCodes.values.toArray
    println(valuesToArray.length) // 3
    println(valuesToArray.foreach(v => print(v + ","))) // CA,NY,VT,() --- () is for what ???????
  }

  def tryImmutableCollection() = {

    createMutableCollection()
    convertImmutableCollectionToMutableOne()


    def createMutableCollection() = {
      // creating mutable collection
      val mutableList: mutable.Buffer[Int] = collection.mutable.Buffer(10, 20, 30) // Mutable version of a List is Buffer
      val mutableSet: mutable.Set[Int] = collection.mutable.Set(10, 20, 30, 10)
      val mutableMap: mutable.Map[String, String] =
        collection.mutable.Map(("California", "CA"),
          ("New York", "NY"),
          ("Vermont", "VT"))
    }

    def convertImmutableCollectionToMutableOne() = {

      // converting immutable collection to mutable one
      val someNumbers = List(10, 20, 30) // immutable list

      // Step 1 - using <CollectionType>.newBuilder[ElementType], create a mutable Builder
      val mutableListBuilder: mutable.Builder[Int, List[Int]] = List.newBuilder[Int] // it is a ListBuffer
      // Step 2 - using foreach, add all the values you want from immutable list to mutableListBuilder
      someNumbers.foreach(number => mutableListBuilder += (number)) // += is an overloaded operator
      // Step 3 - use .result on mutableListBuilder
      val mutableList: List[Int] = mutableListBuilder.result()
      println(mutableList) // List(10, 20, 30)

    }

  }

  // Arrays are fixed length and you can modify its elements.
  def tryArrays() = {
    val fiveInts = new Array[Int](5)
    println(fiveInts.foreach(print(_))) // 0 0 0 0 0 ()  --- () ???

    fiveInts(0) = 10
    println(fiveInts.foreach(print(_))) // 10 0 0 0 0 ()  --- () ???

  }

  // Option has two subclasses - None and Some
  def tryOptionAndUtilTry() = {

    def division(a: Int, b: Int): (Option[Double]) = {

      // using if expression
      var result: Option[Double] =
      if (b == 0) None
      else Some(a / b)

      // or  using match expression
      result =
        b match {
          case 0 => None
          case _ => Some(a / b)
        }

      result
    }

    // using option.getOrElse
    var res = division(1, 1)
    println(res.getOrElse("can not be divided by 0")) // 1.0
    res = division(1, 0)
    println(res.getOrElse("can not be divided by 0")) // can not be divided by 0

    // Using match expression
    res = division(1, 1)

    val finalResult =
      res match {
        case None => "can not be divided by 0"
        case Some(anything) => anything
      }
    println(finalResult) // 1.0


    // util.Try
    // Option saves us from NullPointerException
    // util.Try can save us from any type of exception
    // It has two subclasses : Success and Failure
    // Success is wrapper of result value
    // Failure is a wrapper of an exception
    val stateCodes: Map[String, String] =
    Map(("California", "CA"),
      ("New York", "NY"),
      ("Vermont", "VT"))

    // stateCodes.get("Georgia") will return None
    // stateCodes("Georigia") will throw NoSuchElementException

    var triedMaybeString: Try[String] = Try(stateCodes("Georgia"))
    println(triedMaybeString) // Failure(java.util.NoSuchElementException: key not found: Georgia)
    // if you do triedMaybeString.get, then it will throw NoSuchElementException

    triedMaybeString = Try(stateCodes("California"))
    println(triedMaybeString) // Success(CA)

    // or using match expression
    val result: String = triedMaybeString match {
      case Failure(anything) => "something went wrong"
      case Success(anything) => anything
    }
    println(result) // CA

  }



  def tryOperatorOverloading() = {

    class ComplexNumber(r: Double, i: Double) {

      val realPart = r
      val imaginaryPart = i

      // Any operator can be overloaded just like creating a method
      def +(that: ComplexNumber): ComplexNumber = {
        new ComplexNumber(
          that.realPart + this.realPart,
          that.imaginaryPart + this.imaginaryPart)
      }

      override def toString = s"ComplexNumber($realPart, $imaginaryPart)"
    }

    val oneComplexNumber: ComplexNumber = new ComplexNumber(1,3)
    val secondComplexNumber: ComplexNumber = new ComplexNumber(5,6)

    val newComplexNumber: ComplexNumber = oneComplexNumber + secondComplexNumber
    println(newComplexNumber)// ComplexNumber(6.0, 9.0)
  }

}
