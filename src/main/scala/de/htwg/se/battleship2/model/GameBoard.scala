import scala.io.StdIn.readLine
import de.htwg.se.battleship2.model.Filled
import de.htwg.se.battleship2.model.Matrix
import de.htwg.se.battleship2.model.Field


object Main:
  def main(args: Array[String]): Unit =
    val field = new Field(5, 5, Filled.Empty)
    println(field)
    loop(field, 2)
    
  
  def loop(field: Field, loops: Int): Unit =
    loops match 
      case x if x > 0 =>
        println("Eingabe")
        val input = readLine()
        val chars = input.toCharArray
        val x = chars(0)
        val y = chars(1)
        val updatedField: Field = field.putShip(x, y.toString)
        val updatedLoop = loops - 1
        println(updatedField)
        loop(updatedField, updatedLoop)
      case 0 =>
        println("Spiel beendet.")
      

    