package de.htwg.se.battleship2

package util

import controller.Controller
import model.Field
import model.Filled
import scala.io.StdIn.readLine

/*Strategy pattern*/
object PlayerMode:
  val input = readLine("\nChoose count of Ships (1-4): ")
  var selectPlayerMode = getInput
  var countShip = getShips
  def getInput: Field =
    selectPlayerMode = input match
      case "1" => oneShip
      case "2" => twoShips
      case "3" => threeShips
      case "4" => fourShips
      case _   => oneShip
    selectPlayerMode
  def oneShip = new Field(2, 2, Filled.Empty)
  def twoShips = new Field(4, 4, Filled.Empty)
  def threeShips = new Field(6, 6, Filled.Empty)
  def fourShips = new Field(9, 9, Filled.Empty)

  def getShips: Int =      
    countShip = input match
        case "1" => 2
        case "2" => 4
        case "3" => 6
        case "4" => 8
        case _   => 2
    countShip
