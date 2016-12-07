object Example30 extends App {

  def concatStrings(strings: String*) = {
    var builtUpString = ""
    for (s <- strings) builtUpString = builtUpString + " " + s
    builtUpString
  }

  concatStrings("A", "B", "C")
}