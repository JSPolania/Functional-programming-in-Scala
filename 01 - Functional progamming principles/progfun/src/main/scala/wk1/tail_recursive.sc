def factorial(n:Int):Int = {
  if (n == 0) 1 else n * factorial(n - 1)
}

factorial(5)

// tail recursive version

def factIter(n:Int):Int = {
  def loop(x:Int, acc:Int):Int =
    if (x == 0) acc else loop(x - 1, x * acc)
  loop(n, 1)
}

factIter(5)

