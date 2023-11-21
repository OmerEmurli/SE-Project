package de.htwg.se.battleship2.model

case class Field(matrix: Matrix[Filled]):

  def this(row: Int, col: Int, filling: Filled) =
    this(new Matrix(row, col, filling))

  def bar(length: Int = 5, cellNum: Int = 5) =
    ("+" + "-" * length) * cellNum + "+" + "\n"

  def letters(size: Int = colSize) =
    " " * 2 + ('A' until ('A' + colSize).toChar)
      .map(_.toString)
      .mkString(" " * 3) + "\n"

  def cells(row: Int, length: Int = 5): String =
    val cellRow = matrix
      .row(row)
      .map(_.toString)
      .map(" " * ((length - 1) / 2) + _ + " " * ((length - 1) / 2))
      .mkString("|", "|", "|")
    val rowNumber =
      " " + (row + 1).toString // Startnummer bei 1 für die Zeilennummerierung
    cellRow + rowNumber + "\n"

  def mesh(lenght: Int = 3) =
    letters(5) + (0 until rowSize)
      .map(cells(_, lenght))
      .mkString(
        bar(lenght, colSize),
        bar(lenght, colSize),
        bar(lenght, colSize)
      )

  def put(filling: Filled, x: Int, y: Int) =
    copy(matrix.replaceCell(x, y, filling))

  def letterToNumber(letter: Char): Int =
    val uppercaseLetter = letter.toUpper
    uppercaseLetter - 'A'

  def putShip(letter: Char, number: String): Field =
    val x = letterToNumber(letter)
    val y = number.toInt - 1
    put(Filled.Ship, y, x)

  override def toString = mesh()

  val rowSize = matrix.rowSize
  val colSize = matrix.colSizeEven
