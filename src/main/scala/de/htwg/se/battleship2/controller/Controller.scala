package de.htwg.se.battleship2
package controller

import model.Field
import model.Matrix
import util.Observable

case class Controller(var field: Field) extends Observable:
  def put(letter: Char, number: String): Field =
    val placedShip: Field = field.putShip(letter, number)
    field = placedShip
    placedShip
  notifyObservers  
  override def toString: String = field.toString
  
