case class GameBoard(rows: Int, cols: Int) {

  val eol: String = sys.props("line.separator")

  def border(cols: Int): String = "  X" + "  X" * cols + "  X" + eol

  def letters(cols: Int): String = {
    val letters = ('A' until ('A' + cols).toChar).map(_.toString).mkString("  ")
    s"     $letters" + eol
  }

  def generateBoard(rows: Int, cols: Int): String = {
    val rowIndices = (0 until rows).map(i => (i + 1).toString)
    val gameBoardRows = (0 until rows).map { i =>
      (0 until cols).map(row => "  -").mkString("X", "", "  X")
    }
    val boardWithIndices = rowIndices
      .zip(gameBoardRows)
      .map { case (idx, row) =>
        s"$idx $row" + eol
      }
      .mkString
    s"$boardWithIndices"
  }

  def printBoard(): String =
    letters(cols) + border(cols) + generateBoard(rows, cols) + border(cols)
}

object Main {
  def main(args: Array[String]): Unit = {
    val gameBoard = GameBoard(rows = 5, cols = 5)
    println("Welcome to Battleship2\n")
    println(gameBoard.printBoard())

  }
}
