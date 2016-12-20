package myexamples.covariance

/**
  * @author Tushar Chokshi @ 12/19/16.
  */

// http://docs.scala-lang.org/tutorials/tour/lower-type-bounds
// >:  is a symbol for lowerbound type. U >: T mean U is a super type of T
object CovarianceExample1 extends App {

  /*case class ListNode[T](h: T, t: ListNode[T]) {

    def head: T = h

    def tail: ListNode[T] = t

    def prepend(elem: T): ListNode[T] = {
      ListNode(elem, this)
    }

  }*/

  // The program above implements a linked list with a prepend operation.
  // Unfortunately, this type is invariant in the type parameter of class ListNode; i.e. ListNode[String] is not a subtype of ListNode[Any].
  // With the help of variance annotations we can express such a subtype semantics:

  /*
  http://stackoverflow.com/questions/14584830/covariant-type-t-occurs-in-contravariant-position

  ListNode[+T] means if U <: T (U is a child class of T), then ListNode[U] <: ListNode[T]

        Any
        |
      -----------
      |         |
     String     Emp
        |
     -------
    |       |
    S1       S2

  ListNode[+T] means if it is valid to do Any any = new String(), then it should be valid to do ListNode[Any] anyListNode = new ListNode[String]
  So, now you should be able to do anyListNode.prepend(new Emp(...)). To make that possible, you need to have 'def prepend[U >: T]'. It should be able to accept any child class object of Any class or an object of Any class.
  If you do 'def prepend[T]' ,then it won't be possible. So, Scala doesn't allow that. It will give an error 'error: covariant type T occurs in contravariant position in type T of value elem'

  */
  class ListNode[+T](h: T, t: ListNode[T]) {
    // +T means a type variable T should be used at Covariant position. It means you should pass U >: T to it.
    def head: T = h

    def tail: ListNode[T] = t

    // Unfortunately, below line does not compile because a covariance annotation is only possible if the type variable is used only in covariant positions.
    // Since type variable T appears as a parameter type of method prepend, this rule is broken.
    // With the help of a lower type bound, though, we can implement a prepend method where T only appears in covariant positions.
    //def prepend(elem: T): ListNode[T] = new ListNode(elem, this)
    def prepend[U >: T](elem: U): ListNode[U] = new ListNode(elem, this)


    override def toString = s"ListNode($head,$tail)"
  }

  case class Emp(val name: String)

  // creating ListNode[String]
  var stringListNode: ListNode[String] = new ListNode[String]("abc", null)
  // adding another string value and creating a new ListNode[String]
  stringListNode = stringListNode.prepend("def")

  // As per the rule of +T (here, +String), you should be able to assign ListNode[String] to ListNod[Any] because you can assign String to Any.
  var anyListNode: ListNode[Any] = stringListNode;
  // adding Emp object to ListNode[Any]. This will be allowed only with 'def prepend[U >: T]'. 'def prepend[T]' will not work.
  anyListNode = anyListNode.prepend(Emp("Tushar")) // ListNode((Emp(Tushar),ListNode(def))) --- what you just did is that you added Emp object to a ListNode[String]

  println(anyListNode) // ListNode(Emp(Tushar),ListNode(def,ListNode(abc,null)))

}
