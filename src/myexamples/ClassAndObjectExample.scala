package myexamples

/**
  * @author Tushar Chokshi @ 12/18/16.
  */

/*
This file has examples of
- class
- object
- main method
- sealed class
- override keyword
- inheritance
- singleton object
- companion object
- apply method
- lazy initialization of a variable
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

// This is a "Companion Object".
// Companion Object has same name as class
// These kind of objects can be used as a factory to create instances of the class. It can have factory methods 'apply'.
// A class and its companion object can access each other’s private members. So, it means that class members become static for all companion objects.

// Why companion object is important in Scala?
// Unlike to Java, Scala has a limitation on constructors. Auxiliary constructor has to somehow called Primary constructor. This basically stops you from calling any methods as a first sentence in any constructor. This is a big limitation.
// Just like Java, a call to another constructor from a constructor has to be the first statement.
// To overcome this limitation of constructor in scala, you need use Companion Object having many apply methods as needed. Each apply method can create an instance of a class. You can call any other method of a class or companion object from apply method and based on that instantiate a class.
object Rectangle {
  def apply(l: Double, w: Double, shapeName: String = "Rectangle"): Rectangle = new Rectangle(l, w, shapeName)
  def apply(l: Double): Rectangle = new Rectangle(l, l, "Rectangle")
}

// In scala, object is a singleton object that can extend a class. Class cannot extend an object.
// Singleton object is a replacement of 'static' keyword of Java.
// Scala thought of that defining members as static creates concurrency problems in the multi-threaded and distributed environments.
// All members of Singleton Object are static.
// Singleton Object cannot have class parameters(constructor)
// Singleton Object can extend a class. Other way round is not true.
object SingletonRectangle extends Rectangle(1, 5) {

  // all members of Singleton Object are static
  val myVar: Int = 10

  // main method can be defined in object (not in a class) because main method is by default static and scala doesn't support static keyword.
  def main(args: Array[String]): Unit = {
    // class creation example
    {
      val rect1: Shape = new Rectangle(5, 5)
      val rect2: Shape = new Rectangle(6, 6)
      val rect3: Shape = new Rectangle(7, 7)

      val rects: List[Shape] = List(rect1, rect2, rect3)
      // val result: List[Int] = rects.map(rect => rect.getArea)
      // or using method reference (_ underscore)
      val result: List[Double] = rects.map(_.getArea)
      println(result) // List(25.0, 36.0, 49.0)
    }

    // lazy initialization of a variable example
    // method calling using 'Operator Notation' instead of traditional 'Dot Notation'
    // Apply method example
    {
      val rect4: Rectangle = new Rectangle(1, 1)
      rect4.lazyVar // Trying to initialize lazy variable
      rect4 getArea // Using Operator notation instead of dot notation (rect4.getArea()). Nothing special needs to be done. Any method can be called using any approach.
    }

    // apply method example
    {
      val rect4: Rectangle = new Rectangle(1, 1)

      // Trying apply method
      // AreaCalculator has apply method. So, by simply doing areaCalculator(rect4) will invoke apply method. You don't have to say areaCalculator.apply(rect4)
      // apply methods are very helpful as factory methods in "Companion Singleton Objects".
      val areaCalculator: AreaCalculator = new AreaCalculator()
      println(areaCalculator(rect4))

      // testing Rectangle's apply method
      // List, Set, Map are exactly using the same approach
      // List is a variable referring to singleton object immutable.List, which extends SeqFactory[List]
      // so when you do List(...), it invokes immutatble.List's apply method.
      //      override def apply[A](xs: A*): List[A] = xs.toList
      // This apply method simply converts passed array to a list.
      val rect5: Rectangle = new Rectangle(5, 6)
      println(rect5(50)) // 50.0
      println(SingletonRectangle(50)) // 50.0

    }

    // Singleton Object example
    {
      println(SingletonRectangle) // Rectangle(1.0, 3.0, Rectangle)
      println(SingletonRectangle.getArea) // 3.0 --- accessing singleton object's members like static members


    }

    // Companion Singleton Object example
    {
      val rectangle: Rectangle = Rectangle(1) // This calls apply(l: Double) of Companion Object Rectangle, which acts like a factory method to return an instance of Rectangle class
      println(rectangle)// Rectangle(1.0, 1.0, Rectangle)
    }
  }
}

