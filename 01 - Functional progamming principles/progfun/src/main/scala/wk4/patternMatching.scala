/**
  * Created by Tato on 27-06-17.
  */

object tato {

  trait Expr {
    def eval:Int = this match {
      case Number(n) => n
      case Sum(x,y) => x.eval + y.eval
    }
    def show:String = this match {
      case Number(n) => n.toString
      case Sum(e1, e2) => e1.show + "+" + e2.show
    }
  }

  case class Number(n: Int) extends Expr
  case class Sum(e1: Expr, e2: Expr) extends Expr

}

object daniela extends App {
  import tato._
  val x = Number(5)
  val y = Number(7)
  val z = Sum(x,y)
  println(z.eval)

}
