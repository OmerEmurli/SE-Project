/*package de.htwg.se.battleship2.model

import scala.io.StdIn

case class GameBoard(rows: Int, cols: Int) {
  val eol: String = sys.props("line.separator")
  val rowIndices: Seq[String] = (0 until rows).map(i => (i + 1).toString)
  val gameBoardRows: Seq[String] = (0 until rows).map { i =>
    (0 until cols).map(row => "  -").mkString("X", "", "  X")
  }

  def border(cols: Int): String = "  X" + "  X" * cols + "  X" + eol

  def letters(cols: Int): String = {
    val letters = ('A' until ('A' + cols).toChar).map(_.toString).mkString("  ")
    s"     $letters" + eol
  }

  def generateBoard(): String = {
    val boardWithIndices = rowIndices
      .zip(gameBoardRows)
      .map { case (idx, row) =>
        s"$idx $row" + eol
      }
      .mkString
    s"${letters(cols)}${border(cols)}$boardWithIndices${border(cols)}"
  }

  def placeShip(coord: String): String = {
    val (row, col) = parseCoordinates(coord) // neue datenstruktur, matrix und zellen
    val rowToUpdate = row - 1

    val updatedRows = gameBoardRows.zipWithIndex.map {
      case (rowStr, rowIndex) =>
        if (rowIndex == rowToUpdate) {
          val indexToReplace =
            col * 3 + 3
          val updatedRow = rowStr.patch(indexToReplace, "O", 1)
          s"$updatedRow"
        } else {
          s"$rowStr"
        }
    }

    val updatedBoardWithIndices = rowIndices
      .zip(updatedRows)
      .map { case (idx, row) =>
        s"$idx $row" + eol
      }a
      .mkString
    s"${letters(cols)}${border(cols)}$updatedBoardWithIndices${border(cols)}"
  }

  private def parseCoordinates(coord: String): (Int, Int) = {
    val col = coord.charAt(0).toInt - 'A'.toInt
    val row = coord.substring(1).toInt
    (row, col)
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val gameBoard = GameBoard(rows = 6, cols = 6)
    println("Welcome to Battleship2 \n")
    println(gameBoard.generateBoard())

    println("Player 1, place Ship 1:")
    val ship1Coord = StdIn.readLine().toUpperCase()
    val updatedBoard1 = gameBoard.placeShip(ship1Coord)
    println("\nUpdated Board after placing Ship 1:\n")
    println(updatedBoard1)

  }
}
 */
