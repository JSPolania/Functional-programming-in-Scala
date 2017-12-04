package streams

object App {
  trait play extends GameDef with StringParserTerrain with Solver {
    val level: String =
      """ooo-------
        |oSoooo----
        |ooooooooo-
        |-ooooooooo
        |-----ooToo
        |------ooo-""".stripMargin

    val tato = neighborsWithHistory(startBlock, List())
    val daniela = newNeighborsOnly(tato, Set(startBlock, Block(Pos(1,2),Pos(1,3))))
    val siara = from(Stream((startBlock, List())), Set(startBlock))
  }

  def main(args: Array[String]): Unit = {

    new play {
      for {i <- siara filter (x => done(x._1))} println(i)
    }

  }

}
