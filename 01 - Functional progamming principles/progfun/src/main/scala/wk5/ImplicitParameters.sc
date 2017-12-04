import math.Ordering

def msort[T](xs: List[T])(implicit ord: Ordering[T]): List[T] = {
  val n = xs.length / 2
  if (n == 0) xs
  else {
    def merge(xs:List[T], ys:List[T]):List[T] = (xs, ys) match {
      case (l, Nil) => l
      case (Nil, l) => l
      case (x :: xs1, y :: ys1) =>
        if (ord.lt(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val (fst, snd) = xs splitAt n
    merge(msort(fst), msort(snd))
  }
}

val nums:List[Int] = List(2, -4, 5, 7, 1)
val fruits:List[String] = List("apple","pineapple","banana","orange")

msort(nums)

msort(fruits)
nums splitAt 2