package de.htwg.se.battleship2.model.fieldComponent.fieldImpl

import de.htwg.se.battleship2.model.fieldComponent.MatrixInterface

case class Matrix(matrix: Vector[Vector[Filled]]) extends MatrixInterface[Filled]:

  override def rowSize: Int = matrix.length
  override def colSize: Int = if matrix.nonEmpty then matrix.head.length else 0
  override def putShip(letter: Char, number: String): MatrixInterface[Filled] =
    val row = number.toInt - 1
    val col = letter - 'A'
    if matrix(row)(col) == Filled.Empty then
      val updatedRow = matrix(row).updated(col, Filled.Ship)
      val updatedMatrix = matrix.updated(row, updatedRow)
      copy(matrix = updatedMatrix)
    else this

  override def attack(letter: Char, number: String): MatrixInterface[Filled] =
    val row = number.toInt - 1
    val col = letter - 'A'
    if matrix(row)(col) == Filled.Ship then
      val updatedRow = matrix(row).updated(col, Filled.Hit)
      val updatedMatrix = matrix.updated(row, updatedRow)
      copy(matrix = updatedMatrix)
    else
      val updatedRow = matrix(row).updated(col, Filled.Miss)
      val updatedMatrix = matrix.updated(row, updatedRow)
      copy(matrix = updatedMatrix)

  override def allShipsPlaced: Boolean = matrix.flatten.contains(Filled.Ship)
  override def allShipsSunk: Boolean = !matrix.flatten.contains(Filled.Ship)
  override def cell(row: Int, col: Int): Filled = matrix(row)(col)
  override def replaceCell(row: Int, col: Int, cell: Filled): MatrixInterface[Filled] =
    val updatedRow = matrix(row).updated(col, cell)
    val updatedMatrix = matrix.updated(row, updatedRow)
    copy(matrix = updatedMatrix)
  override def toString: String = matrix.map(_.mkString(" ")).mkString("\n")
