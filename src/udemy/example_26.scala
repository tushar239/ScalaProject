object Example26 extends App {
  val radius = 10.0

  def getCircleStats(r: Double) = {
    val PI = 3.14
    def getCircleArea(r: Double) = PI * r * r
    def getCircleCircumference(r: Double) = 2 * PI * r

    (getCircleArea(r), getCircleCircumference(r))
  }


}