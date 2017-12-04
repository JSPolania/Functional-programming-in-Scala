import scala.annotation.tailrec

class Rational(x:Int,y:Int) {
  require(y != 0, "denom must be diffierent from 0")
  private val g = gcd(x,y)
  def numer: Int = x / g
  def denom: Int = y / g

  def this(x:Int) = this(x,1)

  private def gcd(a:Int,b: Int):Int ={
    if (b == 0) a else gcd(b, a % b)
  }

  override def toString: String = numer + "/" + denom

  def + (that:Rational): Rational = {
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  }
  def unary_- :Rational = new Rational(- numer,denom)
  def - (that:Rational): Rational = this + -that

  def < (that:Rational): Boolean = {
    numer * that.denom < that.numer * denom
  }
  def max(that:Rational): Rational = {
    if (this < that) that else this
  }
}

val x = new Rational(1,3)
val y = new Rational(5,7)
val z = new Rational(3,2)
x.numer
x.denom
x - y - z
y + y

x < y

x max y





