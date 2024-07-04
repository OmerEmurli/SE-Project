package de.htwg.se.battleship2.model.fieldComponent.fieldImpl

import de.htwg.se.battleship2.model.fieldComponent.{FieldInterface, MatrixInterface}

case class Field(matrix: MatrixInterface[Filled]) extends FieldInterface:

  override def rowSize: Int = matrix.rowSize
  override def colSize: Int = matrix.colSize

  override def putShip(letter: Char, number: String): FieldInterface =
    val row = number.toInt - 1
    val col = letter - 'A'
    if matrix.cell(row, col) == Filled.Empty then
      val updatedMatrix = matrix.replaceCell(row, col, Filled.Ship)
      copy(matrix = updatedMatrix)
    else this

  override def attack(letter: Char, number: String): FieldInterface =
    val row = number.toInt - 1
    val col = letter - 'A'
    if matrix.cell(row, col) == Filled.Ship then
      val updatedMatrix = matrix.replaceCell(row, col, Filled.Hit)
      copy(matrix = updatedMatrix)
    else
      val updatedMatrix = matrix.replaceCell(row, col, Filled.Miss)
      copy(matrix = updatedMatrix)

  override def allShipsPlaced: Boolean = matrix.cell(0, 0) match
    case Filled.Ship => true
    case _           => false

  override def allShipsSunk: Boolean = matrix.allShipsSunk

  override def toString: String = matrix.toString
