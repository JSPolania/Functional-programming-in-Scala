package recfun

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
  }

  /**
   * Exercise 1
   */
    def pascal(c: Int, r: Int): Int = {
      if (c == 0 || c == r) 1
      else pascal(c-1, r-1) + pascal(c,r-1)
    }

  /**
   * Exercise 2
   */
    def balance(chars: List[Char]): Boolean = {
      def letters(c:Char):Int = {
        if (c == '(') 1
        else if (c == ')') -1
        else 0
      }
      def loop(n:Int, l:List[Char]): Boolean = {
        if (l.isEmpty) n == 0
        else if (n < 0) false
        else loop(n + letters(l.head), l.tail)
      }
      loop(0, chars)
    }
  
  /**
   * Exercise 3
   */
    def countChange(money: Int, coins: List[Int]): Int = {
      if (money == 0) 1
      else if (money < 0 || coins.isEmpty) 0
      else countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
