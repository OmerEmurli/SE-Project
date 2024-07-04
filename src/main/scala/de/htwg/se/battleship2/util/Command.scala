package de.htwg.se.battleship2.util

import de.htwg.se.battleship2.model.fieldComponent.FieldInterface

trait Command:
  def doStep(field: FieldInterface): FieldInterface
  def undoStep(field: FieldInterface): FieldInterface
  def redoStep(field: FieldInterface): FieldInterface

class UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(field: FieldInterface, command: Command): FieldInterface =
    undoStack = command :: undoStack
    redoStack = Nil
    command.doStep(field)

  def undoStep(field: FieldInterface): FieldInterface = undoStack match
    case Nil => field
    case head :: stack =>
      undoStack = stack
      redoStack = head :: redoStack
      head.undoStep(field)

  def redoStep(field: FieldInterface): FieldInterface = redoStack match
    case Nil => field
    case head :: stack =>
      redoStack = stack
      undoStack = head :: undoStack
      head.redoStep(field)

case class PutCommand(letter: Char, number: String, var field: FieldInterface) extends Command:
  override def doStep(field: FieldInterface): FieldInterface = field.putShip(letter, number)
  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp

case class AttackCommand(letter: Char, number: String, var field: FieldInterface) extends Command:
  override def doStep(field: FieldInterface): FieldInterface = field.attack(letter, number)
  override def undoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: FieldInterface): FieldInterface =
    val temp = this.field
    this.field = field
    temp
