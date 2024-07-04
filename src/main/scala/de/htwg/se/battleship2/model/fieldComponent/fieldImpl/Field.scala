package de.htwg.se.battleship2.model.fieldComponent.fieldImpl

import de.htwg.se.battleship2.model.fieldComponent.{FieldInterface, MatrixInterface}

case class Field(matrix: MatrixInterface[Filled]) extends FieldInterface:
  override def rowSize: Int = matrix.rowSize
  override def colSize: Int = matrix.colSize
  override def putShip(letter: Char, number: String): FieldInterface = copy(matrix = matrix.putShip(letter, number))
  override def attack(letter: Char, number: String): FieldInterface = copy(matrix = matrix.attack(letter, number))
  override def allShipsPlaced: Boolean = matrix.allShipsPlaced
  override def allShipsSunk: Boolean = matrix.allShipsSunk
  override def toString: String = matrix.toString
