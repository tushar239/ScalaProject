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

// covariant [+T]
// contravariant	[-T]
// invariant	C[T]

// <: upperbound
// >: lowerbound
object CovarianceAndContraVarianceExample {

  class VendingMachine[+A] { // +A means that A is a “covariant type parameter”

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
