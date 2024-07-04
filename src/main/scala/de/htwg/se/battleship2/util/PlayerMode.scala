package de.htwg.se.battleship2.util

import de.htwg.se.battleship2.model.fieldComponent._
import de.htwg.se.battleship2.model.fieldComponent.fieldImpl._

object Playermode:
  def createField(shipCount: Int): FieldInterface =
    val (rows, cols) = shipCount match
      case 1 => (4, 4)
      case 2 => (6, 6)
      case 3 => (8, 8)
      case 4 => (10, 10)
      case _ => (4, 4) // Standardgröße falls unübliche Schiffszahl

    new Field(new Matrix(Vector.tabulate(rows, cols)((_, _) => Filled.Empty)))

  val oneShip: FieldInterface = createField(1)
  val twoShips: FieldInterface = createField(2)
  val threeShips: FieldInterface = createField(3)
  val fourShips: FieldInterface = createField(4)
