
def streamRange(lo: Int, hi: Int):Stream[Int] =
  if (lo > hi) Stream()
  else lo #:: streamRange(lo + 1, hi)




