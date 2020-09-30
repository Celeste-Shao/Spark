def maybeItWillReturnSomething(flag: Boolean): Option[String] = {
  if (flag) Some("Found value") else None
}
val value1 = maybeItWillReturnSomething(true)
val value2 = maybeItWillReturnSomething(false)
value1 getOrElse  "No value"
value2 getOrElse "No value"

val number: Option[Int] = Some(3)
val noNumber: Option[Int] = None
val result1 = number.fold(1)(_ * 3)
val result2 = noNumber.fold(1)(_ * 3)
result1
result2

val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Meechigan")
val mapValues = myMap.values
myMap("MI")

val c = 'a' //unicode for a
val e = '\"'
val f = '\\'

"%c".format(c)
"%c".format(e)
"%c".format(f)

case class Dog(name: String, breed: String)
val d1 = Dog("Scooby", "Doberman")
d1.toString

case class Person(first: String, last: String, age: Int = 0, ssn: String = "")
val p1 = Person("Fred", "Jones", 23, "111-22-3333")

val parts =
  Person.unapply(p1).get