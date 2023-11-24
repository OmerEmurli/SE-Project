package de.htwg.se.battleship2
package controller

import org.scalatest.matchers.should.Matchers._
import org.scalatest.wordspec.AnyWordSpec
import model.Field
import model.Filled

class ControllerSpec extends AnyWordSpec {

  "A Controller" when {
    val initialField = new Field(5, 4, Filled.Empty)
    val controller = Controller(initialField)

    "putting a ship" should {
      "return a new Field with a ship placed at the specified position" in {
        val updatedField = controller.put('A', "1")
        updatedField.toString should include("O") // Check if ship placed at A1
      }
    }

    "returning a string representation" should {
      "return a string representation of the current field state" in {
        controller.toString should include("-") // Check if the initial field state is represented
      }
    }

    // Weitere Tests für andere Eigenschaften und Methoden des Controllers...
  }
}

