package de.htwg.se.battleship2


import aview.TUI
import controller.Controller
import model.Field
import model.Filled
import util.PlayerMode
import aview.Template


@main def setup = TUI(Controller(PlayerMode.selectPlayerMode)).run
