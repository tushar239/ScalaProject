object Example15 extends App {
  val dayOfWeek = "Friday"

  val typeOfDay1 = dayOfWeek match {
    case "Monday" => "Manic Monday"
    case "Sunday" => "Sleepy Sunday"
    case someOtherDay => {
      println(s"Some other day - neither Sunday nor Monday, its $someOtherDay")
      someOtherDay
    }
  }

  val typeOfDay2 = dayOfWeek match {
    case "Monday" => "Manic Monday"
    case "Sunday" => "Sleepy Sunday"
    case _ => {
      val errorString = s"Some other day - neither Sunday nor Monday, its $dayOfWeek"
      //_
    }
  }

}


