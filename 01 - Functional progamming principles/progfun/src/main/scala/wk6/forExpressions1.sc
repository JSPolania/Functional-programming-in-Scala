val n = 5

def isPrime(n:Int): Boolean = (2 until n) forall (d => n % d != 0 )


//option one, nestind higher order functions
(1 to n) flatMap (x => (1 to x) map (y => (x,y))) filter {case (i,j) => isPrime(i+j)}


//option two, using for-expressions
for {x <- 1 to n
     y <- 1 to x
     if isPrime(x+y)
} yield (x,y)

def scalarProduct(xs: List[Double], ys: List[Double]): Double =
  (for {(x, y) <- xs zip ys} yield x * y).sum


scalarProduct(List(1,2,3), List(4,5,6))




