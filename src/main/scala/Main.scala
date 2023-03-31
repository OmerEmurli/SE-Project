import scala.io.StdIn.readLine

object Spielfeld {
  def main(args: Array[String]): Unit = {
    val width = readLine("Bitte geben Sie die Breite des Spielfelds ein: ").toInt
    val height = readLine("Bitte geben Sie die Höhe des Spielfelds ein: ").toInt

    val spielfeld = Array.ofDim[String](width, height)

    for (x <- 0 until width) {
      for (y <- 0 until height) {
        spielfeld(x)(y) = "-"
      }
    }

    printSpielfeld(spielfeld)
  }

  def printSpielfeld(spielfeld: Array[Array[String]]): Unit = {
    for (x <- 0 until spielfeld.length) {
      for (y <- 0 until spielfeld(x).length) {
        print(spielfeld(x)(y) + " ")
      }
      println()
    }
  }
}
