package de.htwg.se.battleship2
package aview

import util.{Observer, GameState}
import controller.controllerComponent.controllerImpl.Controller


/* template pattern */
abstract class Template(controller: Controller) extends Observer:
    controller.add(this)
    def run: Unit =
        update
        gameLoop
    def gameLoop: Unit
    