package calculator

object Polynomial {
  def computeDelta(a: Signal[Double], b: Signal[Double],
      c: Signal[Double]): Signal[Double] = {
    Signal {
      val (aVal, bVal, cVal) = (a(), b(), c())
      scala.math.pow(bVal, 2) - 4 * aVal * cVal
    }
  }

  def computeSolutions(a: Signal[Double], b: Signal[Double],
      c: Signal[Double], delta: Signal[Double]): Signal[Set[Double]] = {
    Signal {
      val dVal = delta()
      if (dVal < 0) Set[Double]()
      else {
        val (aVal, bVal) = (a(), b())
        Set((-bVal + scala.math.sqrt(dVal)) / (2 * aVal), (-bVal - scala.math.sqrt(dVal)) / (2 * aVal))
      }
    }
  }
}
