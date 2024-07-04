import de.htwg.se.battleship2.aview.{GUI, TUI}
import de.htwg.se.battleship2.controller.controllerComponent._
import de.htwg.se.battleship2.controller.controllerComponent.controllerImpl._
import de.htwg.se.battleship2.DefaultDependencies.{given, _} // Importiere die given-Instanzen korrekt

@main def setup: Unit = {
  val controller = summon[ControllerInterface]
  val tui = new TUI(using controller)
  val gui = new GUI(using controller)
  gui.visible = true
  tui.run
}
