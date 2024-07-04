package de.htwg.se.battleship2.aview

import scala.io.StdIn.readLine
import de.htwg.se.battleship2.controller.controllerComponent.ControllerInterface
import de.htwg.se.battleship2.util.GameState
import de.htwg.se.battleship2.util.Observer

class TUI(using controller: ControllerInterface) extends Observer {
  controller.add(this)

  def run: Unit = {
    var input: String = ""
    while input != "q" do {
      println(controller.getField.toString)
      input = readLine()
      processInput(input)
    }
  }

  override def update: Unit = println(controller.getField.toString)

  def processInput(input: String): Unit = {
    val patternShip = "p([A-J])(\\d)".r
    val patternAttack = "a([A-J])(\\d)".r
    input match {
      case "q"                                                                =>
      case "u"                                                                => controller.undo
      case "r"                                                                => controller.redo
      case patternShip(col, row) if controller.state == GameState.Placing     => controller.put(col.charAt(0), row)
      case patternAttack(col, row) if controller.state == GameState.Attacking => controller.attack(col.charAt(0), row)
      case _ => println("Invalid input. Use p[A-J][0-9] to place ships or a[A-J][0-9] to attack, u to undo, r to redo, q to quit.")
    }
  }
}
