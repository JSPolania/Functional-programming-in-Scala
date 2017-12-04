val siara = Stream(1,2,3,4,5)

val tato = Set[Int]()

tato + 5

//(siara foldLeft tato)(_ + _)


//val states = for {
//  (blk, moves) <- initial
//  next <- newNeighborsOnly(neighborsWithHistory(blk, moves), explored)
//} yield next
//val newExplored = (states foldLeft explored)(_ + _._1)