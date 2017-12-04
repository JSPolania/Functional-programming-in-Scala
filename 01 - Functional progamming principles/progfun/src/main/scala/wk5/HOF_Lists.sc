def squareList(xs:List[Int]):List[Int] =  xs match {
  case Nil     => Nil
  case y :: ys => y * y :: squareList(ys)
}

squareList(List(2,4,6))

def squareList2(xs:List[Int]): List[Int]=
  xs map (x => x * x)

squareList2(List(2,4,6))

val nums:List[Int] = List(2, -4, 5, 7, 1)
val fruits:List[String] = List("apple","pineapple","banana","orange")


nums filter (_ > 0)

nums filterNot (_ > 0) //inverse of filter

nums partition(_ > 0) //tuple of filter and filternot (one traverse)

nums takeWhile(x => x < 6)

nums dropWhile(x => x < 6)

nums span(_ < 6) /// return tuple

def pack[T](xs: List[T]): List[List[T]] = xs match {
  case Nil => Nil
  case x :: xs1 =>
    val (first, rest) = xs span (y => y == x)
    first :: pack(rest)
}

val code = List("a", "a", "a", "b", "c", "c", "a")

pack(code)

def encode[T](xs: List[T]): List[(T,Int)] =
  pack(xs) map (x => (x.head, x.length))

encode(code)