package de.htwg.se.battleship2.controller

import de.htwg.se.battleship2.model.{Field, Filled}
import de.htwg.se.battleship2.util.{Observable, GameState, Command, PutCommand, AttackCommand, UndoManager}

case class Controller(var field: Field) extends Observable {
  private val undoManager = new UndoManager
  var state: GameState = GameState.Placing

  def setTotalShipsToPlace(count: Int): Unit = {
    field = count match {
      case 1 => new Field(2, 2, Filled.Empty)
      case 2 => new Field(4, 4, Filled.Empty)
      case 3 => new Field(6, 6, Filled.Empty)
      case 4 => new Field(9, 9, Filled.Empty)
      case _ => new Field(2, 2, Filled.Empty)
    }
    field.totalShipsToPlace = count
    state = GameState.Placing
    notifyObservers
  }

  def put(letter: Char, number: String): Field = {
    field = undoManager.doStep(field, PutCommand(letter, number, field))
    notifyObservers
    if (field.isShipPlacementComplete) {
      state = GameState.Attacking
    } else {
      state = GameState.Placing
    }
    field
  }

  def attac(letter: Char, number: String): Field = {
    field = undoManager.doStep(field, AttackCommand(letter, number, field))
    notifyObservers
    if (field.allShipsSunk) {
      state = GameState.Finished
      println("All ships sunk. Game over!")
    }
    field
  }

  def undo: Field = {
    field = undoManager.undoStep(field)
    notifyObservers
    if (field.isShipPlacementComplete) {
      state = GameState.Attacking
    } else {
      state = GameState.Placing
    }
    field
  }

  def redo: Field = {
    field = undoManager.redoStep(field)
    notifyObservers
    if (field.isShipPlacementComplete) {
      state = GameState.Attacking
    } else {
      state = GameState.Placing
    }
    field
  }

  def gameEnd: Boolean = field.gameIsFinished

  def stateHandler(state: GameState): Unit = {
    this.state = state
    notifyObservers
  }

  override def toString: String = field.toString
}
