

def queens(n:Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else
      for {
        queens <- placeQueens(k - 1)
        col <- 0 until n
        if isSafe(col, queens)
      } yield col :: queens
  placeQueens(n)
}

def isSafe(col: Int, queens:List[Int]):Boolean = {
  val row = queens.length
  val queensWithRow = (row - 1 to 0 by -1) zip queens
  queensWithRow forall {
    case (r, c) => col != c && math.abs(col - c) != row - r
  }
}

def queens2(n:Int): Set[List[Int]] = {
  def placeQueens(k: Int): Set[List[Int]] =
    if (k == 0) Set(List())
    else placeQueens(k - 1) flatMap
      (queens => (0 until n) map (col => col :: queens)) filter (l => isSafe(l.head, l.tail))
  placeQueens(n)
}

queens(4)

queens2(4)

//val x = Set(1,2,3)
//
//x contains 4
//x contains 1
//
//4 +: x

