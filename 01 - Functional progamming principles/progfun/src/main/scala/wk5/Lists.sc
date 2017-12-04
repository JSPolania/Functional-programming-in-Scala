def last[T](xs: List[T]): T = xs match {
  case List()   => throw new Error("Last of empty list")
  case List(x)  => x
  case y :: ys  => last(ys)
}

last(List(1,3,4,5,6))

def init[T](xs: List[T]): List[T] = xs match {
  case List()   => throw new Error("init of empty list")
  case List(x)  => List()
  case y :: ys  => y :: init(ys)
}

init(List(1,3,4,5,6))

def concat[T](xs: List[T], ys: List[T]): List[T] = xs match {
  case List()   => ys
  case z :: zs  => z :: concat(zs, ys)
}

concat(List(1,2,3), List(7,8))

def reverse[T](xs: List[T]): List[T] = xs match {
  case List()   => xs
  case y :: ys  => reverse(ys) ++ List(y)
}

reverse(List(1,2,3,4))

def removeAt[T](n: Int, xs: List[T]): List[T] =
  (xs take n) ::: (xs drop n+1)

removeAt(0,List(1,2,3,4))