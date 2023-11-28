package de.htwg.se.battleship2
package controller

import model.Field
import model.Matrix
import util.Observable
import util.GameState

case class Controller(var field: Field) extends Observable:
  def put(letter: Char, number: String): Field =
    val placedShip: Field = field.putShip(letter, number)
    field = placedShip
    placedShip
  def attac(letter: Char, number: String): Field =
    val attacShip: Field = field.attac(letter, number)
    field = attacShip
    attacShip
  def gameEnd: Boolean = field.gameIsFinished  
  def stateHandler(state: GameState): Unit = state match
    case GameState.Finished => sys.exit
    case GameState.Running |GameState.Placing => notifyObservers
  //def publish(field: Field): Field =


  override def toString: String = field.toString
  
