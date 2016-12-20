package myexamples


/**
  * @author Tushar Chokshi @ 12/19/16.
  */

// http://docs.scala-lang.org/tutorials/tour/lower-type-bounds
// >:  is a symbol for lowerbound type. U >: T mean U is a super type of T
object LowerTypeBoundExample extends App {

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

  class ListNode[+T](h: T, t: ListNode[T]) {// +T means a type variable T should be used at Covariant position. It means you should pass U >: T to it.
    def head: T = h

    def tail: ListNode[T] = t

    // Unfortunately, below line does not compile because a covariance annotation is only possible if the type variable is used only in covariant positions.
    // Since type variable T appears as a parameter type of method prepend, this rule is broken.
    // With the help of a lower type bound, though, we can implement a prepend method where T only appears in covariant positions.
    //def prepend(elem: T): ListNode[T] = new ListNode(elem, this)
    def prepend[U >: T](elem: U): ListNode[U] = new ListNode(elem, this)

  }


  val empty: ListNode[Null] = new ListNode(null, null)

  val strList: ListNode[String] = empty.prepend("hello").prepend("world")

  val anyList: ListNode[Any] = strList.prepend(12345)


}
