object Example14 extends App {
  val dayOfWeek = "Friday"

  val typeOfDay1 = dayOfWeek match {
    case "Monday" => "Manic Monday"
    case "Sunday" | "Saturday" => "Lazy weekend"
    case "Tuesday" | "Wednesday" | "Thursday" | "Friday" => "Other working day"
  }


  val typeOfDay2 = dayOfWeek match {
    case "Monday" => "Manic Monday"
    case "Tuesday" | "Wednesday" | "Thursday" | "Friday" => "Other working day"
    case someOtherDay if someOtherDay == "Sunday" => "Sleepy Sunday"
    case someOtherDay if someOtherDay == "Saturday" => "Sizzing Saturday"
  }
}




