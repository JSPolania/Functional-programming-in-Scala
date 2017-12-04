/**
  * Created by Tato on 27-06-17.
  */

/**
  * Created by Tato on 27-06-17.
  */

object polymorphism {

  trait List[T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def size: Int
    def nth(n:Int): T
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty: Boolean = false
    override def toString(): String =  head + "::" + tail
    def size: Int = {
      def loop(acc:List[T],i:Int):Int = {
        if (acc.isEmpty) i
        else loop(acc.tail,i + 1)
      }
      loop(this,0)
    }
    def nth(n:Int): T = {
      def loop(acc:List[T],i:Int):T = {
        if (acc.isEmpty) throw new IndexOutOfBoundsException
        else if (i == n) acc.head
        else loop(acc.tail, i + 1)
      }
      loop(this, 1)
    }
  }

  class Nil[T] extends List[T] {
    val isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
    def size: Nothing = throw new NoSuchElementException("Nil.size")
    def nth(n:Int): Nothing = throw new NoSuchElementException("Nil.nth")
    override def toString: String = "Nil"
  }
  def singleton[T](elem: T):List[T] = new Cons[T](elem, new Nil)

  object List {
    def apply[T](x1: T, x2: T): List[T] = new Cons[T](x1, new Cons[T](x2, new Nil))
    def apply[T](x: T): List[T] = new Cons[T](x, new Nil)
    def apply[T](): List[T] = new Nil
  }
}
object Main extends App {
  import polymorphism._
  val x = new Cons(1, new Cons(2, new Cons(3, new Cons(5, new Cons(7, new Nil)))))
  println(x)
  println(x.size)
  println(x.nth(2))
  val y = List(5,2)
  println(y)
}



