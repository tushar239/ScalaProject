package myexamples

/**
  * @author Tushar Chokshi @ 12/20/16.
  */
// https://www.tutorialspoint.com/scala/scala_extractors.htm
object ExtractorExample {
  def main(args: Array[String]) {
    println ("Apply method : " + apply("Zara", "gmail.com"));
    println ("Unapply method : " + unapply("Zara@gmail.com"));
    println ("Unapply method : " + unapply("Zara Ali"));
  }

  // The injection method
  def apply(user: String, domain: String) = {
    user +"@"+ domain
  }

  // The extraction method
  // The unapply method is what turns Test class into an extractor and it reverses the construction process of apply. Where apply takes two strings and forms an email address string out of them, unapply takes an email address and returns potentially two strings: the user and the domain of the address.
  // The unapply must also handle the case where the given string is not an email address. That's why unapply returns an Option-type over pairs of strings. Its result is either Some (user, domain) if the string str is an email address with the given user and domain parts, or None, if str is not an email address.
  def unapply(str: String): Option[(String, String)] = {
    val parts = str split "@"

    if (parts.length == 2){
      Some(parts(0), parts(1))
    } else {
      None
    }
  }
}
