package de.htwg.se.battleship2.model.fieldComponent

trait MatrixInterface[T]:
  def rowSize: Int
  def colSize: Int
  def cell(row: Int, col: Int): T
  def replaceCell(row: Int, col: Int, cell: T): MatrixInterface[T]
  def allShipsSunk: Boolean
  override def toString: String
