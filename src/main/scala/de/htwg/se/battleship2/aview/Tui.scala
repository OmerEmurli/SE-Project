package de.htwg.se.battleship2
package aview

import util.Observer
import scala.io.StdIn.readLine
import controller.Controller
import util.PlayerMode
import util.GameState

class TUI(controller: Controller) extends Template(controller):

  override def update: Unit = println("\n" + controller.field.toString)

  def gameLoop: Unit =
    placeShips(PlayerMode.countShip)

  def placeShips(shipsToPlace: Int): Unit =
    if shipsToPlace > 0 then
      println(s"Place your ship ($shipsToPlace ships left):")
      val input = readLine()
      input match
        case "u" =>
          controller.undo
          placeShips(shipsToPlace + 1)
        case "r" =>
          controller.redo
          placeShips(shipsToPlace - 1)
        case _ =>
          val chars = input.toCharArray
          if chars.length >= 2 then
            val x = chars(0)
            val y = chars(1).toString
            controller.put(x, y)
            placeShips(shipsToPlace - 1)
          else
            println("Invalid input. Please enter coordinates like 'A1'.")
            placeShips(shipsToPlace)
    else attackPhase(shipsToPlace)

  def attackPhase(shipsToPlace: Int = 0): Unit =
    println("Attack phase begins. Enter coordinates to attack.")
    while !controller.gameEnd do
      val input = readLine()
      input match
        case "u" =>
          controller.undo
          if shipsToPlace > 0 then placeShips(shipsToPlace + 1)
        case "r" =>
          controller.redo
        case _ =>
          val chars = input.toCharArray
          if chars.length >= 2 then
            val x = chars(0)
            val y = chars(1).toString
            controller.attac(x, y)
            println("Attack successful!")
          else println("Invalid input. Please enter coordinates like 'A1'.")
    println("Game finished.")
