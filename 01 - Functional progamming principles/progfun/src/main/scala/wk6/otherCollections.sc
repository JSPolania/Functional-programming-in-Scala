val xs = Array(1,2,3,44)

xs map (_ * 2)

val s = "Hello World"

s filter (_.isUpper)
s.exists(_.isUpper)

s forall (_.isUpper)

val pairs = List(1,2,3) zip s
pairs.unzip

s.flatMap (c => List('.', c))

xs.sum

(1 to 3) flatMap (x => (1 to 5) map (y => (x, y)))

def scalarProduct(xs: Vector[Double], ys: Vector[Double]): Double =
  xs zip ys map { case (a, b) => a * b } sum

def isPrime(n:Int): Boolean = (2 until n) forall (d => n % d != 0 )

isPrime(5)

isPrime(4)

