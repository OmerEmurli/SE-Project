package de.htwg.se.battleship2.model

case class Field(matrix: Matrix[Filled], var totalShipsToPlace: Int = 4) {

  def this(row: Int, col: Int, filling: Filled) =
    this(new Matrix(row, col, filling))

  def bar(length: Int = 3, cellNum: Int = 2) =
    ("+" + "-" * length) * cellNum + "+" + "\n"

  def letters(size: Int = colSize) =
    " " * 2 + ('A' until ('A' + colSize).toChar).map(_.toString).mkString(" " * 3) + "\n"

  def cells(row: Int, length: Int): String =
    val cellRow = matrix.row(row).map(_.toString).map(" " * ((length - 1) / 2) + _ + " " * ((length - 1) / 2)).mkString("|", "|", "|")
    val rowNumber =
      " " + (row + 1).toString
    cellRow + rowNumber + "\n"

  def mesh(length: Int = 3) =
    letters(5) + (0 until rowSize).map(cells(_, length)).mkString(bar(length, colSize), bar(length, colSize), bar(length, colSize))

  def put(filling: Filled, x: Int, y: Int) =
    copy(matrix.replaceCell(x, y, filling))

  def letterToNumber(letter: Char): Int =
    val uppercaseLetter = letter.toUpper
    uppercaseLetter - 'A'

  def putShip(letter: Char, number: String): Field =
    val x = letterToNumber(letter)
    val y = number.toInt - 1
    put(Filled.Ship, y, x)

  def attac(letter: Char, number: String): Field =
    val x = letterToNumber(letter)
    val y = number.toInt - 1

    matrix.cell(y, x) match
      case Filled.Ship =>
        put(Filled.Hit, y, x)
      case _ =>
        println("Missed!")
        put(Filled.Miss, y, x)

  def gameIsFinished: Boolean = !matrix.vec.flatten.contains(Filled.Ship)

  def isShipPlacementComplete: Boolean = {
    val placedShips = matrix.vec.flatten.count(_ == Filled.Ship)
    placedShips >= totalShipsToPlace
  }

  def allShipsSunk: Boolean = {
    val ships = matrix.vec.flatten.count(_ == Filled.Ship)
    val hits = matrix.vec.flatten.count(_ == Filled.Hit)
    ships == 0 && hits == totalShipsToPlace
  }

  override def toString = mesh()

  val rowSize = matrix.rowSize
  val colSize = matrix.colSize
}
