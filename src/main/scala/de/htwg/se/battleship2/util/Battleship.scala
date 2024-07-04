package de.htwg.se.battleship2

import de.htwg.se.battleship2.controller.controllerComponent.controllerImpl.Controller
import de.htwg.se.battleship2.aview.{TUI, GUI}
import de.htwg.se.battleship2.util.Playermode

@main def setup =
  val controller = Controller(Playermode.oneShip) // Initialisiere mit einem Standardfeld
  val tui = TUI(controller)
  val gui = new GUI(controller)
  gui.visible = true
  tui.run
