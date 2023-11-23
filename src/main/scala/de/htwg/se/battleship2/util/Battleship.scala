package de.htwg.se.battleship2

import aview.TUI
import controller.Controller
import model.Field
import model.Filled


@main def main: Unit =
  TUI(Controller(new Field(5, 4, Filled.Empty))).run
