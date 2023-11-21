package de.htwg.se.battleship2.model

class PlaceShip(field: Field):

  def letterToNumber(letter: Char): Int =
    val uppercaseLetter = letter.toUpper
    uppercaseLetter - 'A'

  def putShip(letter: Char, number: String): Field =
    val x = letterToNumber(letter)
    val y = number.toInt - 1
    field.put(Filled.Ship, y, x)
