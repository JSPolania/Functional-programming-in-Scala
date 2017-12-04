def mapFun[T, U](xs: List[T], f: T => U): List[U] =
  (xs foldRight List[U]())((x,y) => f(x) :: y)


mapFun(List(1,2,3), (x:Int) => x + 1)

def lengthFun[T](xs: List[T]): Int =
  (xs foldRight 0)( (x,y) => y + 1 )

lengthFun(List(1,2,3,4,2,4))


