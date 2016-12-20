package myexamples.covariance

/**
  * @author Tushar Chokshi @ 12/19/16.
  */

// http://docs.scala-lang.org/tutorials/tour/upper-type-bounds
// UpperBound symbol is <:
// LowerBound symbol is >:
// They are like U extends T and U super T kind of generics in Java.
object UpperAndLowerTypeBoundExample {

  abstract class Animal {
    def name: String
  }


  abstract class Pet extends Animal {}
  class Cat extends Pet {
    override def name: String = "Cat"
  }
  class Dog extends Pet {
    override def name: String = "Dog"
  }



  class WildAnimal extends Animal {
    override def name: String = "Wild"
  }
  class Lion extends WildAnimal {
    override def name: String = "Lion"
  }



  // UpperBound Parameter Type example (<:)
  class CageForPets[P <: Pet](p: P) { // it is same as java's "Cage<P extends Pet>"
    def pet: P = p
  }

  // LowerBound Parameter Type example (>:)
  class CageForWildAnimals[W >: Lion](w: W) {// it is same as java's "Cage<W Super Pet>"
    def wild: W = w
  }

  def main(args: Array[String]): Unit = {
    {
      var dogCage = new CageForPets[Dog](new Dog)
      var catCage = new CageForPets[Cat](new Cat)
      /* Cannot put Lion in a pet cage as Lion is not a Pet. */
      //  var lionCage = new CageForPets[Lion](new Lion)
    }
    {
      val lionCage: CageForWildAnimals[Animal] = new CageForWildAnimals[Animal](new Lion)
      /* Cannot put Dog in a wild animal cage as Dog is not a wild animal. */
      // val dogCage: CageForWildAnimals[Dog] = new CageForWildAnimals[Dog](new Dog)
    }
  }


}
