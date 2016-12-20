package myexamples.covariance

/**
  * @author Tushar Chokshi @ 12/19/16.
  */
// http://blog.kamkor.me/Covariance-And-Contravariance-In-Scala/
// Imagine that your employer promised a new soft drink vending machine. Covariance means that in its place he can install a cola or tonic water vending machine because both cola and tonic water are subtypes of soft drink.

// In Java, Covariance and ContraVariance are not possible, but you can do something like below in java
// class VendingMachine<A>
// public void <T extends SoftDrink> install(VendingMachine<T> softDrinkVM)   --- this is kind of Covariance
// public void <T super SoftDrink> install(VendingMachine<T> drink)   --- this is kind of ContraVariance

// invariant	C[T]
// covariant [+T]  --- it means that if U >: T, then C[U] >: C[T]. So, if T t = new U() is allowed then C[T] = new C[U] should be allowed
// contravariant	[-T] --- it means that if U <: T, then C[U] <: C[T]. So, if U t = new T() is allowed then C[U] = new C[T] should be allowed

// <: upperbound  - same as Java's generics C<U extends T>
// >: lowerbound  - same as Java's generics C<U super T>
object CovarianceAndContraVarianceExample {

  class VendingMachine[+T] { // +T means that T is a “covariant type parameter”

  }

  class Drink {

  }
  class SoftDrink extends Drink {

  }
  class Cola extends SoftDrink {

  }
  class TonicWater extends SoftDrink {

  }

  class Juice extends SoftDrink {

  }
  class OrangeJuice extends Juice {

  }
  class AppleJuice extends Juice {

  }

  // The method install accepts a VendingMachine of type SoftDrink or subtypes of SoftDrink (Cola and TonicWater).
  // This is possible because type parameter A is prefixed with a +.
  def install(softDrinkVM: VendingMachine[SoftDrink]): Unit ={

  }




  class Item {

  }

  class PlasticItem extends Item {

  }
  class PlasticBottle extends PlasticItem {

  }

  class PaperItem extends Item {

  }
  class Newspaper extends PaperItem {

  }



  class GarbageCan[-A] {// AA means that A is a “contravariant type parameter”

  }
  def setGarbageCanForPlastic(gc: GarbageCan[PlasticItem]): Unit = {

  }


  def main(args: Array[String]): Unit = {
    // ..... Covariant .....
    // covariant subtyping
    install(new VendingMachine[Cola])
    install(new VendingMachine[TonicWater])

    // invariant
    install(new VendingMachine[SoftDrink])

    //install(new VendingMachine[Drink]) // invalid

    // ..... ContraVariant .....
    // contravariant subtyping
    setGarbageCanForPlastic(new GarbageCan[Item])
    // invariant
    setGarbageCanForPlastic(new GarbageCan[PlasticItem])
    //setGarbageCanForPlastic(new GarbageCan[PlasticBottle]) // invalid

  }

}
