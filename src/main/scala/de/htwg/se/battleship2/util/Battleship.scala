package de.htwg.se.battleship2

import aview.{GUI, TUI}
import controller.Controller
import util.PlayerMode

@main def setup =
  val controller = Controller(PlayerMode.oneShip) // Initialisiere mit einem Standardfeld
  val tui = TUI(controller)
  val gui = new GUI(controller)
  gui.visible = true
  tui.run
