def squrtIter(guess:Double, x:Double):Double = {
  if (goodEnough(guess, x)) guess
  else squrtIter(improve(guess, x), x)
}

def goodEnough(guess:Double, x:Double):Boolean ={
  math.abs(guess * guess - x) / x < 0.001
}

def improve(guess:Double, x:Double):Double = {
  (guess + x / guess) / 2
}

def sqrt(n:Double): Double = squrtIter(1.0, n)

sqrt(3e-10)

sqrt(3e10)




