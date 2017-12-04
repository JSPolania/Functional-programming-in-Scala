package streams

object playground {
  trait play extends GameDef with StringParserTerrain

  val level =
    """ooo-------
      |oSoooo----
      |ooooooooo-
      |-ooooooooo
      |-----ooToo
      |------ooo-""".stripMargin

  terrain(Pos(0,0))



}