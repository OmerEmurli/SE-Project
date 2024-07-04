package de.htwg.se.battleship2.controller.controllerComponent.controllerImpl

import de.htwg.se.battleship2.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship2.model.fieldComponent.FieldInterface
import de.htwg.se.battleship2.util._

class Controller(var field: FieldInterface) extends ControllerInterface with Observable:
  private var totalShipsToPlace: Int = 0
  private var shipsPlaced: Int = 0
  private var gameState: GameState = GameState.Placing
  private val undoManager = new UndoManager

  override def setTotalShipsToPlace(total: Int): Unit =
    totalShipsToPlace = total
    shipsPlaced = 0
    gameState = GameState.Placing
    field = Playermode.createField(total)
    notifyObservers

  override def put(col: Char, row: String): Unit =
    if gameState == GameState.Placing && shipsPlaced < totalShipsToPlace then
      val command = PutCommand(col, row, field)
      field = undoManager.doStep(field, command)
      shipsPlaced += 1
      if shipsPlaced == totalShipsToPlace then gameState = GameState.Attacking
      notifyObservers

  override def attack(col: Char, row: String): Unit =
    if gameState == GameState.Attacking then
      val command = AttackCommand(col, row, field)
      field = undoManager.doStep(field, command)
      if field.allShipsSunk then gameState = GameState.Finished
      notifyObservers

  override def undo: Unit =
    field = undoManager.undoStep(field)
    notifyObservers

  override def redo: Unit =
    field = undoManager.redoStep(field)
    notifyObservers

  override def state: GameState = gameState

  def getField: FieldInterface = field
