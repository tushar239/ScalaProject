
def voteForMrTrump(x:String):String = {
  println("go D-J-T")
  x
}

def sayHelloToMrTrump(name: => String):String = {
  println(s"Hello $name")
  println(s"Yet again, hello! $name")
  name
}

sayHelloToMrTrump("Vitthal")
sayHelloToMrTrump(sayHelloToMrTrump("Vitthal"))

def sayHelloToMrTrump(name:String):String = {
  println(s"Hello $name")
  println(s"Yet again, hello! $name")
  name
}

sayHelloToMrTrump("Vitthal")
sayHelloToMrTrump(voteForMrTrump("Vitthal"))




