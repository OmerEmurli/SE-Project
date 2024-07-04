package de.htwg.se.battleship2.model.fieldComponent.fieldImpl

import de.htwg.se.battleship2.model.fieldComponent.MatrixInterface

case class Matrix(matrix: Vector[Vector[Filled]]) extends MatrixInterface[Filled]:

  override def rowSize: Int = matrix.length
  override def colSize: Int = if matrix.nonEmpty then matrix.head.length else 0

  override def cell(row: Int, col: Int): Filled = matrix(row)(col)

  override def replaceCell(row: Int, col: Int, cell: Filled): MatrixInterface[Filled] =
    val updatedRow = matrix(row).updated(col, cell)
    val updatedMatrix = matrix.updated(row, updatedRow)
    copy(matrix = updatedMatrix)

  override def allShipsSunk: Boolean = !matrix.flatten.contains(Filled.Ship)

  override def toString: String = matrix.map(_.mkString(" ")).mkString("\n")
