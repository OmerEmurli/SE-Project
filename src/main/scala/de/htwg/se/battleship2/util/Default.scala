package de.htwg.se.battleship2

import de.htwg.se.battleship2.model.fieldComponent._
import de.htwg.se.battleship2.model.fieldComponent.fieldImpl._
import de.htwg.se.battleship2.controller.controllerComponent._
import de.htwg.se.battleship2.controller.controllerComponent.controllerImpl._

object DefaultDependencies {
  given FieldInterface: FieldInterface = new Field(new Matrix(Vector.fill(10, 10)(Filled.Empty)))
  given ControllerInterface: ControllerInterface = new Controller(summon[FieldInterface])
}
