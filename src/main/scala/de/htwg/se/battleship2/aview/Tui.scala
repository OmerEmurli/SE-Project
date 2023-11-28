package de.htwg.se.battleship2
package aview


import util.Observer
import scala.io.StdIn.readLine
import controller.Controller
import model.Field
import util.PlayerMode
import util.GameState



class TUI(controller: Controller) extends Template(controller):

    override def update: Unit = println("\n" + controller.field.toString)
    
    def gameLoop: Unit =
        controller.stateHandler(checkState.handle(controller.gameEnd))
        gameLoop
    
  
    /*def loop(field: Field, loops: Int): Unit =
     loops match 
      case x if x > 0 =>
        println("put ship")
        val input = readLine()
        val chars = input.toCharArray
        val x = chars(0)
        val y = chars(1)
        val updatedField: Field = controller.put(x, y.toString)
        val updatedLoop = loops - 1
        println(updatedField)
        loop(updatedField, updatedLoop)
      case 0 =>
        println("attac ship")
        val input = readLine()
        val chars = input.toCharArray
        val x = chars(0)
        val y = chars(1)
        val updatedField1: Field = controller.attac(x, y.toString)
        println(updatedField1)
        println("End of Game.")
      */
    var counts = PlayerMode.countShip
    object checkState:
        var state = GameState.Running
        def handle(check: Boolean): GameState =
            state = check match
                case true  => finished
                case false => analyseInput()
            state
        def finished: GameState = 
            GameState.Finished
        def analyseInput(): GameState =
           counts match
            case  x if x > 0 =>
              println("put ship")
              val input1 = readLine()
              val chars = input1.toCharArray
              val x = chars(0)
              val y = chars(1)
              controller.put(x, y.toString)
              counts = counts - 1
              GameState.Placing
            case 0 =>
              println("attac ship")
              val input = readLine()
              val chars = input.toCharArray
              val x = chars(0)
              val y = chars(1)
              controller.attac(x, y.toString)
              GameState.Running

               