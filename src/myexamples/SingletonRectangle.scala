package myexamples

/**
  * @author Tushar Chokshi @ 12/18/16.
  */

// sealed class can inherited only in the same file
abstract sealed class Shape(sN: String) {
  protected val shapeName: String = sN;

  def getArea: Double

  override def toString = s"Shape($shapeName)"
}

// Unlike to Java, here you can define a default constructor like this
// Rectangle class extends Shape class. There is no 'super' keyword like Java. It passes shapeName to Shape class as 'extends Shape(shapeName)
class Rectangle(l: Double, w: Double, shapeName: String = "Rectangle") extends Shape(shapeName) {
  // you can provide default value to class parameter to avoid auxiliary constructors (constructor overloading)
  // Another way to create member variables
  // class Rectangle1(val length: Int, val width: Int)

  // member variables
  // Unlike to Java, by default class members are given 'public' access. There is no 'default' access like Java.
  val length: Double = l
  val width: Double = w

  def apply(something: Double): Double = something

  // This is an example of lazy variable. Unless you access it, it won't be initialized.
  lazy val lazyVar: Double = {
    println("Trying to initialize lazy var") // This statement won't be printed, unless you try to access lazyVar variable
    100
  }

  // Auxiliary constructor
  /*
  def this(l:Double, w: Double, d:Double) ={
    this(l, w)
  }
  */

  override def getArea: Double = {
    // using member variables in a member method
    length * width
    // or
    //l * w
  }

  // you can override parent class' method using 'override' keyword. 'override' keyword is not optional like Java's @Override
  override def toString = s"Rectangle($length, $width, $shapeName)"

}

class Square(s: Double) extends Rectangle(s, s, "Square") {

}

class AreaCalculator {
  // apply method is a special method called 'default'. It can be invoked without calling it.
  def apply(r: Rectangle) = r.length * r.width
}

// In scala, object is a singleton object that can extend a class. Class cannot extend an object.
// Singleton object is a replacement of 'static' keyword of Java.
// Scala thought of that defining members as static creates concurrency problems in the multi-threaded and distributed environments.
// All members of Singleton Object are static.
// Singleton Object cannot have class parameters(constructor)
// Singleton Object can extend a class. Other way round is not true.
object SingletonRectangle extends Rectangle(1, 3) {
  // all members of Singleton Object are static
  val myVar: Int = 10

  def main(args: Array[String]): Unit = {
    // main method can be defined in singleton object (not in a class) because main method is by default static and scala doesn't support static keyword.
    println(SingletonRectangle) // Rectangle(1.0, 3.0, Rectangle)
    println(SingletonRectangle.getArea) // 3.0 --- accessing singleton object's members like static members


    // testing Rectangle's apply method
    // List, Set, Map are exactly using the same approach
    // List is a variable referring to singleton object immutable.List, which extends SeqFactory[List]
    // so when you do List(...), it invokes immutatble.List's apply method.
    //      override def apply[A](xs: A*): List[A] = xs.toList
    // This apply method simply converts passed array to a list.
    val rect1: Rectangle = new Rectangle(5, 6)
    println(rect1(50)) // 50.0
    println(SingletonRectangle(50)) // 50.0


  }
}
