package quickcheck

object heapPlayground extends BinomialHeap with IntHeap {

  val daniela = insert(5, insert(3, empty))
  val tato = insert(4, insert(7, empty))
  def list(h:H, l:List[A]):List[A] = if (isEmpty(h)) l else list(deleteMin(h), findMin(h) :: l)
  val result = list(meld(daniela, tato), Nil)
}

object heapPlay2 extends BinomialHeap with IntHeap with Bogus1BinomialHeap {
  val daniela = insert(5, insert(3, empty))
  val tato = insert(4, insert(7, empty))
  def list(h:H, l:List[A]):List[A] = if (isEmpty(h)) l else list(deleteMin(h), findMin(h) :: l)
  val result = list(meld(daniela, tato), Nil)
}

object play3 extends BinomialHeap with IntHeap {
  def main(args: Array[String]): Unit = {
    println(heapPlayground.result)
    println(heapPlay2.result)
  }
}