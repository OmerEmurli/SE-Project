import scala.io.StdIn.readLine
import de.htwg.se.battleship2.model.Filled
import de.htwg.se.battleship2.model.Matrix
import de.htwg.se.battleship2.model.Field
import de.htwg.se.battleship2.model.PlaceShip

object Main:
  def main(args: Array[String]): Unit =
    val field = new Field(6, 4, Filled.Empty)
    println(field)
    loop(field)

  def loop( field: Field): Unit =
    while (true) {
      println("Eingabe")
      val input = readLine()
      val chars = input.toCharArray
      val x = chars(0)
      val y = chars(1)
      val updatedField: Field = field.putShip(
        x,
        y.toString()
      ) // Beispiel: Hier die Benutzerinteraktion einfügen
      println(updatedField)
      loop(updatedField)
    }
      

    
