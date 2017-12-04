def sqrt(n:Double): Double = {
  def squrtIter(guess:Double):Double = {
    if (goodEnough(guess)) guess
    else squrtIter(improve(guess))
  }
  def goodEnough(guess:Double):Boolean ={
    math.abs(guess * guess - n) / n < 0.001
  }
  def improve(guess:Double):Double = {
    (guess + n / guess) / 2
  }
  squrtIter(1.0)
}

sqrt(3e-10)

sqrt(3e10)


/** scope va por bloques**/

val x = 0
def f(y:Int): Int = y + 1
val result = {
  val x = f(3) // result tiene visibilidad de lo que este afuera
  x * x //este x se refiere a la variable x definida en el bloque, sobreescribe a x en el namespace
} + x //como esta efuera del bloque, se refiere a val que se definido y tiene scope global