package de.htwg.se.battleship2.model.fieldComponent

import de.htwg.se.battleship2.model.fieldComponent.fieldImpl.{Matrix, Filled}

trait FieldInterface:
  def rowSize: Int
  def colSize: Int
  def matrix: MatrixInterface[Filled] // Diese Methode stellt sicher, dass die Matrix zurückgegeben wird.
  def putShip(letter: Char, number: String): FieldInterface
  def attack(letter: Char, number: String): FieldInterface
  def allShipsPlaced: Boolean
  def allShipsSunk: Boolean
  override def toString: String
