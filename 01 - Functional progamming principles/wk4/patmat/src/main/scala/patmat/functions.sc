import patmat.Huffman.Leaf

def mergeCodeTables(a: List[Int], b: List[Int]): List[Int] = a match {
      case code :: rest => mergeCodeTables(rest, code :: b)
      case Nil => b
    }


mergeCodeTables(List(1,2,3), List(4,5,6))
