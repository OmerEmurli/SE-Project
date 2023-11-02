import scala.io.StdIn.readLine

object Spielfeld2 {
  def main(args: Array[String]): Unit = {
    val width = readLine("Please enter the width of the game field: ").toInt
    val height = readLine("Please enter the height of the game field: ").toInt

    val spielfeld = Array.ofDim[String](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        spielfeld(x)(y) = "-"
      }
    }

    printSpielfeld(spielfeld)
    val spieler1Schiffe = platzierenSchiffe("Player 1", spielfeld)
    printSpielfeld(spieler1Schiffe)
    val spieler2Schiffe = platzierenSchiffe("Player 2", spielfeld)
    printSpielfeld(spieler2Schiffe)
  }

  def printSpielfeld(spielfeld: Array[Array[String]]): Unit = {
    val width = spielfeld.length
    val height = spielfeld(0).length

    def printRowSeparator(): Unit = {
      print("  ")
      for (i <- 0 until width) {
        print("--")
      }
      println()
    }

    print("   ")
    for (i <- 0 until width) {
      print(s" ${('A' + i).toChar} ")
    }
    println()
    printRowSeparator()

    for (y <- 0 until height) {
      print(f"$y%2d|")
      for (x <- 0 until width) {
        print(s" ${spielfeld(x)(y)} ")
      }
      println("|")
    }

    printRowSeparator()
  }

  def platzierenSchiffe(
      spielerName: String,
      spielfeld: Array[Array[String]]
  ): Array[Array[String]] = {
    println(s"$spielerName, please place your ships.")
    var schiffePlatziert = 0
    val width = spielfeld.length
    val height = spielfeld(0).length
    val schiffe = Array.ofDim[String](width, height)

    while (schiffePlatziert < 2) {
      println(s"Please enter the coordinates for ship ${schiffePlatziert + 1}.")
      val input = readLine().toUpperCase()
      if (input.length() == 2) {
        val x = input.charAt(0) - 'A'
        val y = input.charAt(1) - '0'
        if (x >= 0 && x < width && y >= 0 && y < height) {
          if (schiffe(x)(y) != "O") {
            schiffe(x)(y) = "O"
            schiffePlatziert += 1
            println("Ship placed.")
          } else {
            println("There's already a ship at that location.")
          }
        } else {
          println("Invalid coordinates.")
        }
      } else {
        println("Invalid input.")
      }
      printSpielfeld(schiffe)
    }

    schiffe
  }
}
