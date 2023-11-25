package de.htwg.se.battleship2
package aview


import util.Observer
import scala.io.StdIn.readLine
import controller.Controller
import model.Field



class TUI(controller: Controller) extends Observer:

    override def update: Unit = println("\n" + controller.field.toString)

    
    controller.add(this)
    def run =
        println(controller.field.toString)
        loop(controller.field, 2)
    
  
    def loop(field: Field, loops: Int): Unit =
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
        println("End of Game.")
