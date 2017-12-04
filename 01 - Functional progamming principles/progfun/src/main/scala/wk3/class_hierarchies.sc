abstract class IntSet {
  def incl(x:Int): IntSet
  def contains(x:Int): Boolean
  def union(other: IntSet): IntSet
}

object Empty extends IntSet {
  def contains(x:Int):Boolean = false
  def incl(x:Int):IntSet = new NonEmpty(x, Empty, Empty)
  def union(other: IntSet): IntSet = other
  override def toString = "."
}

class NonEmpty(elem:Int, left:IntSet, right:IntSet) extends IntSet {
  def contains(x:Int): Boolean = {
    if (x < elem) left contains elem
    else if (x > elem) right contains elem
    else true
  }
  def incl(x: Int): IntSet = {
    if (x < elem) new NonEmpty(elem, left incl x, right)
    else if (x > elem) new NonEmpty(elem, left, right incl x)
    else this
  }
  def union(other: IntSet): IntSet =
    ((left union right) union other) incl elem
  override def toString: String = "[" + left + elem + right + "]"
}

def time[R](block: => R): R = {
  val t0 = System.nanoTime()
  val result = block    // call-by-name
  val t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")
  result
}
val t1 = new NonEmpty(3, new NonEmpty(1, Empty,Empty), new NonEmpty(5, Empty,Empty))
val t2 = new NonEmpty(7, new NonEmpty(2, Empty,Empty), new NonEmpty(9, Empty,Empty))
val t3 = t1 union t2
val t = time({t1 union t2})

//(left union other union right) incl elem
// Elapsed time: 73342ns
//(left union right union other) incl elem
//Elapsed time: 68137ns
//((left union right) union other) incl elem
//Elapsed time: 79656ns
//(left union (right union other)) incl elem
//Elapsed time: 133644ns

//  substitucion con ((left union right) union other) incl elem
//  [[.1.]3[.5.]] union [[.2.]7[.9.]]
//  (([.1.] union [.5.]) union [[.2.]7[.9.]]) incl 3
//  ((((. union .) union [.5.]) incl 1) union [[.2.]7[.9.]]) incl 3
//  (((. union [.5.]) incl 1) union [[.2.]7[.9.]]) incl 3
//  (([.5.] incl 1) union [[.2.]7[.9.]]) incl 3
//  ([[.1.]5.] union [[.2.]7[.9.]]) incl 3
//  ((([.1.] union .) union [[.2.]7[.9.]]) incl 5) incl 3
//  (((((. union .) union .) incl 1) union [[.2.]7[.9.]]) incl 5) incl 3
//  ((((. union .) incl 1) union [[.2.]7[.9.]]) incl 5) incl 3
//  (((. incl 1) union [[.2.]7[.9.]]) incl 5) incl 3
//  (([.1.] union [[.2.]7[.9.]]) incl 5) incl 3
//  ((((. union .) union [[.2.]7[.9.]]) incl 1 ) incl 5) incl 3
//  (((. union [[.2.]7[.9.]]) incl 1 ) incl 5) incl 3
//  (([[.2.]7[.9.]] incl 1 ) incl 5) incl 3
//  ([[[.1.]2.]7[.9.]] incl 5) incl 3
//  [[[.1.]2[.5.]]7[.9.]] incl 3
//  [[[.1.]2[[.3.]5.]]7[.9.]]







