package de.htwg.se.battleship2.model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import org.scalatest.Assertions._

class FilledSpec extends AnyWordSpec {
    "Filled" when {
        "accessing" should {
            "have the right string representation" in {
                val empty = Filled.Empty
                empty.toString should be("-")
                val ship = Filled.Ship
                ship.toString should be("O")
                val hit = Filled.Hit
                hit.toString should be("H")
               
            } 
        }
    }
}
