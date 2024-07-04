package de.htwg.se.battleship2.model.fieldComponent

trait MatrixInterface[T]:
  def rowSize: Int
  def colSize: Int
  def putShip(letter: Char, number: String): MatrixInterface[T]
  def attack(letter: Char, number: String): MatrixInterface[T]
  def allShipsPlaced: Boolean
  def allShipsSunk: Boolean
  def cell(row: Int, col: Int): T
  def replaceCell(row: Int, col: Int, cell: T): MatrixInterface[T]
  override def toString: String
