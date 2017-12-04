def from(n:Int): Stream[Int] = n #:: from(n + 1)


from(5) take(3) toList

val multiplesOf4 = from(0) map (_ * 4)

multiplesOf4 take 5 toList

def sieve(s:Stream[Int]): Stream[Int] =
  s.head #:: sieve(s.tail filter (_ % s.head != 0))

