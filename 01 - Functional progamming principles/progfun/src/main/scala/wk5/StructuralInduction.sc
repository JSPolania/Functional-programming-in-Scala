def factorial(n:Int):Int =
  if (n == 0) 1               //first clause
  else n * factorial(n - 1)   //second clause