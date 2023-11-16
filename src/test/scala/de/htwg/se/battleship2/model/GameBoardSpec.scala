import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._

class GameBoardSpec extends AnyWordSpec {

  val gameBoard = new GameBoard(5, 5)

  "GameBoard" should {
    "have a Boarder as String of form '  X  X  X  X  X  X  X' " in {
      val expectedBorder = "  X  X  X  X  X  X  X" + gameBoard.eol
      val actualBorder = gameBoard.border(5)

      actualBorder should be(expectedBorder)
    }

    "have letters in form  '     A  B  C  D  E'" in {
      val expectedLetters = "     A  B  C  D  E" + gameBoard.eol
      val actualLetters = gameBoard.letters(
        5
      )

      actualLetters should be(expectedLetters)
    }

    "have a scalable border" in {
      val gameBoard1 = new GameBoard(1, 1)
      val gameBoard2 = new GameBoard(2, 2)
      val gameBoard3 = new GameBoard(3, 3)

      val expectedBorder1 = "  X  X  X" + gameBoard1.eol
      val expectedBorder2 = "  X  X  X  X" + gameBoard2.eol
      val expectedBorder3 = "  X  X  X  X  X" + gameBoard3.eol

      val actualBorder1 = gameBoard1.border(1)
      val actualBorder2 = gameBoard2.border(2)
      val actualBorder3 = gameBoard3.border(3)

      actualBorder1 should be(expectedBorder1)
      actualBorder2 should be(expectedBorder2)
      actualBorder3 should be(expectedBorder3)

    }
    "GameBoard" should {
      "have a board in form of" in {
        val result = gameBoard.generateBoard(5, 5)
        val expected = "1 X  -  -  -  -  -  X" + gameBoard.eol +
          "2 X  -  -  -  -  -  X" + gameBoard.eol +
          "3 X  -  -  -  -  -  X" + gameBoard.eol +
          "4 X  -  -  -  -  -  X" + gameBoard.eol +
          "5 X  -  -  -  -  -  X" + gameBoard.eol

        result should be(expected)
      }
      "have a scalable board in form of" in {
        val gameBoard1x1 = new GameBoard(1, 1)
        val gameBoard2x1 = new GameBoard(2, 1)
        val gameBoard2x2 = new GameBoard(2, 2)

        val expected1x1 = "1 X  -  X" + gameBoard1x1.eol
        val expected2x1 = "1 X  -  X" + gameBoard2x1.eol +
          "2 X  -  X" + gameBoard2x1.eol
        val expected2x2 = "1 X  -  -  X" + gameBoard2x2.eol +
          "2 X  -  -  X" + gameBoard2x2.eol

        val result1x1 = gameBoard1x1.generateBoard(1, 1)
        val result2x1 = gameBoard2x1.generateBoard(2, 1)
        val result2x2 = gameBoard2x2.generateBoard(2, 2)

        result1x1 shouldBe expected1x1
        result2x1 shouldBe expected2x1
        result2x2 shouldBe expected2x2
      }
      "printBoard() for 1x1" in {
        val gameBoard1x1 = new GameBoard(1, 1)

        val expectedBoard = "     A" + gameBoard1x1.eol +
          "  X  X  X" + gameBoard1x1.eol +
          "1 X  -  X" + gameBoard1x1.eol +
          "  X  X  X" + gameBoard1x1.eol

        val resultBoard = gameBoard1x1.printBoard()

        resultBoard shouldBe expectedBoard
      }
      "printBoard() for 2x2" in {
        val gameBoard2x2 = new GameBoard(2, 2)

        val expectedBoard = "     A  B" + gameBoard2x2.eol +
          "  X  X  X  X" + gameBoard2x2.eol +
          "1 X  -  -  X" + gameBoard2x2.eol +
          "2 X  -  -  X" + gameBoard2x2.eol +
          "  X  X  X  X" + gameBoard2x2.eol

        val resultBoard = gameBoard2x2.printBoard()

        resultBoard shouldBe expectedBoard
      }

    }
  }
}
