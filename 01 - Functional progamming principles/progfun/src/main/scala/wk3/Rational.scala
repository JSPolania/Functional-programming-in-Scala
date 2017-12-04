package wk3

/**
  * Created by Tato on 21-06-17.
  */
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

