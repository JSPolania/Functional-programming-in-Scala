/**
  * Created by Tato on 27-06-17.
  */

object polymorphism1 {

  trait List[+T] {
    def isEmpty: Boolean
    def head: T
    def tail: List[T]
    def prepend [U >: T](elem:U): Cons[U] = new Cons(elem,this)
  }

  class Cons[T](val head: T, val tail: List[T]) extends List[T] {
    def isEmpty: Boolean = false
    override def toString(): String =  head + "::" + tail
  }

  object Nil extends List[Nothing] {
    val isEmpty: Boolean = true
    def head: Nothing = throw new NoSuchElementException("Nil.head")
    def tail: Nothing = throw new NoSuchElementException("Nil.tail")
    override def toString: String = "Nil"
  }
}
