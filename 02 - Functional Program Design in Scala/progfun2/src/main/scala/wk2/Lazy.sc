def expr = {
  val x = {println("x"); 1}
  lazy val y = {println("y"); 2}
  def z  = {println("z"); 3}
  z + y + x + z + y + x
}

expr


/**
  * val is evaluated as soon is reached
  * lazy val is evaluated once, when si called later in the code
  * def is evaluated everytime is called
  */

