
def sum(f: Int => Int): (Int,Int) => Int = {
  def sumF(a:Int, b: Int): Int = {
    if (a > b) 0
    else f(a) + sumF(a + 1, b)
  }
  sumF
}

def sumInts: (Int,Int) => Int = sum(x => x)

sumInts(3,5)

def sumCubes = sum(x => x * x * x)

sumCubes(3,5)

sum(x => x * x * x)(1,10)

def product(f:Int=>Int)(a:Int,b:Int):Int = {
  def loop(step:Int,acc:Int): Int =
    if (step > b) acc else loop(step + 1, acc * f(step))
  loop(a,1)
}

def fact(n:Int) = product(x => x)(1, n)

fact(5)

def prodSquares:(Int,Int)=>Int = product(x => x * x)

prodSquares(2,5)

def mapReduce(f:Int=>Int, combine:(Int,Int)=>Int, id:Int)(a:Int,b:Int):Int = {
  if (a > b) id else combine(f(a), mapReduce(f,combine,id)(a + 1, b))
}
def fact1(n:Int):Int = mapReduce(x=>x, (x,y)=>x*y, 1)(1,n)

fact1(5)