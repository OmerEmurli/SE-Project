package de.htwg.se.battleship2.controller.controllerComponent

import de.htwg.se.battleship2.util.Observable
import de.htwg.se.battleship2.model.fieldComponent.FieldInterface
import de.htwg.se.battleship2.util.GameState

trait ControllerInterface extends Observable:
  def setTotalShipsToPlace(total: Int): Unit
  def put(col: Char, row: String): Unit
  def attack(col: Char, row: String): Unit
  def undo: Unit
  def redo: Unit
  def state: GameState
  def getField: FieldInterface
