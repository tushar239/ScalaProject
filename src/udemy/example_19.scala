object Example19 extends App {
  val daysOfWeekList = List("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

  for (day <- daysOfWeekList if day == "Manic Monday") {
    println(day)
  }
}

