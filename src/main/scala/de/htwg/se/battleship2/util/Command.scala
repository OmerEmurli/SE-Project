/*package de.htwg.se.battleship2.util

import de.htwg.se.battleship2.model.Field
import scala.util.{Try, Success}

trait Command {
  def execute(): Try[Field]
  def undo(): Try[Field]
}

case class PutShipCommand(field: Field, letter: Char, number: String) extends Command {
  private var previousState: Option[Field] = None

  override def execute(): Try[Field] = Try {
    previousState = Some(field.copy())
    field.putShip(letter, number)
  }

  override def undo(): Try[Field] = Try {
    previousState match {
      case Some(state) => state
      case None        => throw new IllegalStateException("Cannot undo before execution")
    }
  }
}

case class AttackCommand(field: Field, letter: Char, number: String) extends Command {
  private var previousState: Option[Field] = None

  override def execute(): Try[Field] = Try {
    previousState = Some(field.copy())
    field.attac(letter, number)
  }

  override def undo(): Try[Field] = Try {
    previousState match {
      case Some(state) => state
      case None        => throw new IllegalStateException("Cannot undo before execution")
    }
  }
}
 */

package de.htwg.se.battleship2.util

import de.htwg.se.battleship2.model.Field

trait Command {
  def doStep(field: Field): Field
  def undoStep(field: Field): Field
  def redoStep(field: Field): Field
}

class UndoManager:
  private var undoStack: List[Command] = Nil
  private var redoStack: List[Command] = Nil

  def doStep(field: Field, command: Command): Field =
    undoStack = command :: undoStack
    redoStack = Nil
    command.doStep(field)

  def undoStep(field: Field): Field = undoStack match
    case Nil => field
    case head :: stack =>
      undoStack = stack
      redoStack = head :: redoStack
      head.undoStep(field)

  def redoStep(field: Field): Field = redoStack match
    case Nil => field
    case head :: stack =>
      redoStack = stack
      undoStack = head :: undoStack
      head.redoStep(field)

case class PutCommand(letter: Char, number: String, var field: Field) extends Command:
  override def doStep(field: Field): Field = field.putShip(letter, number)
  override def undoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp

case class AttackCommand(letter: Char, number: String, var field: Field) extends Command:
  override def doStep(field: Field): Field = field.attac(letter, number)
  override def undoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp
  override def redoStep(field: Field): Field =
    val temp = this.field
    this.field = field
    temp
