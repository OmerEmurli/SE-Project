package de.htwg.se.battleship2.aview

import scala.swing._
import scala.swing.event._
import de.htwg.se.battleship2.controller.Controller
import de.htwg.se.battleship2.util.Observer
import de.htwg.se.battleship2.model.Filled
import de.htwg.se.battleship2.util.GameState
import java.awt.Color

class GUI(controller: Controller) extends MainFrame with Observer {

  title = "Battleship"
  preferredSize = new Dimension(800, 600)
  background = new Color(50, 50, 50) // Dunkler Hintergrund

  controller.add(this)

  val cellSize = 30

  // Initial GUI components
  val startPanel = new BorderPanel {
    background = new Color(50, 50, 50) // Dunkler Hintergrund
    val welcomeLabel = new Label("Welcome to Battleship") {
      foreground = Color.WHITE
      font = new Font("Arial", java.awt.Font.BOLD, 24)
      horizontalAlignment = Alignment.Center
    }
    val instructionLabel = new Label("Choose the amount of ships:") {
      foreground = Color.WHITE
      font = new Font("Arial", java.awt.Font.PLAIN, 16)
      horizontalAlignment = Alignment.Center
    }
    val buttonPanel = new FlowPanel {
      background = new Color(50, 50, 50)
      val buttons = for (i <- 1 to 4) yield new Button {
        text = i.toString
        background = new Color(100, 100, 100)
        foreground = Color.WHITE
        reactions += { case ButtonClicked(_) =>
          controller.setTotalShipsToPlace(i)
          startGame()
        }
      }
      contents ++= buttons
    }
    layout(welcomeLabel) = BorderPanel.Position.North
    layout(instructionLabel) = BorderPanel.Position.Center
    layout(buttonPanel) = BorderPanel.Position.South
  }

  val statusLabel = new Label {
    text = "Placing ships..."
    foreground = Color.WHITE
  }

  var grid: GridPanel = _

  def createGrid(): GridPanel = {
    val newGrid = new GridPanel(controller.field.rowSize + 1, controller.field.colSize + 1) {
      preferredSize = new Dimension((controller.field.colSize + 1) * cellSize, (controller.field.rowSize + 1) * cellSize)
      background = new Color(50, 50, 50) // Dunkler Hintergrund

      contents += new Label("") // Top-left corner empty cell

      for (col <- 1 to controller.field.colSize) {
        contents += new Label(('A' + col - 1).toChar.toString) {
          preferredSize = new Dimension(cellSize, cellSize)
          horizontalAlignment = Alignment.Center
          foreground = Color.WHITE
        }
      }

      for (row <- 0 until controller.field.rowSize) {
        contents += new Label((row + 1).toString) {
          preferredSize = new Dimension(cellSize, cellSize)
          horizontalAlignment = Alignment.Center
          foreground = Color.WHITE
        }

        for (col <- 0 until controller.field.colSize) {
          val cell = new Button {
            preferredSize = new Dimension(cellSize, cellSize)
            reactions += { case ButtonClicked(_) =>
              handleCellClick(row, col)
            }
          }
          contents += cell
        }
      }
    }
    newGrid
  }

  val undoButton = new Button {
    text = "Undo"
    background = new Color(100, 100, 100)
    foreground = Color.WHITE
    reactions += { case ButtonClicked(_) =>
      controller.undo
    }
  }

  val redoButton = new Button {
    text = "Redo"
    background = new Color(100, 100, 100)
    foreground = Color.WHITE
    reactions += { case ButtonClicked(_) =>
      controller.redo
    }
  }

  def startGame(): Unit = {
    grid = createGrid()
    contents = new BorderPanel {
      layout(grid) = BorderPanel.Position.Center
      layout(statusLabel) = BorderPanel.Position.South
      layout(new FlowPanel {
        contents += undoButton
        contents += redoButton
      }) = BorderPanel.Position.North
    }
    this.validate()
    this.repaint()
  }

  contents = startPanel

  override def update: Unit = {
    if (grid != null) {
      for (row <- 0 until controller.field.rowSize; col <- 0 until controller.field.colSize) {
        val index = (row + 1) * (controller.field.colSize + 1) + (col + 1)
        val cell = grid.contents(index).asInstanceOf[Button]
        val state = controller.field.matrix.cell(row, col)
        state match {
          case Filled.Ship =>
            cell.text = ""
            cell.background = new Color(0, 255, 0) // Green for ship
          case Filled.Hit =>
            cell.text = ""
            cell.background = new Color(255, 0, 0) // Red for hit
          case Filled.Miss =>
            cell.text = ""
            cell.background = new Color(255, 255, 0) // Yellow for miss
          case Filled.Empty =>
            cell.text = ""
            cell.background = new Color(173, 216, 230) // Light blue for empty
        }
      }
    }

    statusLabel.text = controller.state match {
      case GameState.Placing   => "Placing ships..."
      case GameState.Attacking => "Attack phase"
      case GameState.Finished  => "Game over!"
      case _                   => ""
    }
  }

  private def handleCellClick(row: Int, col: Int): Unit = {
    if (controller.state == GameState.Placing) {
      controller.put(('A' + col).toChar, (row + 1).toString)
    } else if (controller.state == GameState.Attacking) {
      controller.attac(('A' + col).toChar, (row + 1).toString)
      if (controller.state == GameState.Finished) {
        Dialog.showMessage(contents.head, "All ships sunk. Game over!", title = "Game Over")
      }
    }
  }

  visible = true
}
