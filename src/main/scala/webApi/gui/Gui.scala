package webApi.gui

import java.awt.Color

import webApi.business.MainApi

import scala.swing.RichWindow.Undecorated
import scala.swing._
import scala.swing.event._


class Gui(serverApi: MainApi) extends MainFrame with Undecorated {
  // Fields
  title = "Home server.Server"
  preferredSize = new Dimension(800, 480)
  val bgColor = new Color(30, 30, 30)
  var clickLocation: Point = new Point(0, 0)

  import BorderPanel.Position._

  contents = new BorderPanel() {
    opaque = true
    background = bgColor

    // Menu Panel
    layout += new BorderPanel {
      // Properties
      background = new Color(20, 20, 20)

      // Events listeners
      listenTo(mouse.moves)
      listenTo(mouse.clicks)
      // Handlers
      reactions += {
        case MouseDragged(_, p, _) =>
          Reposition(p)
        case MousePressed(_, p, _, _, _) =>
          clickLocation = p
      }

      // Exit Button
      layout += new UndecoratedButton(60) {
        focusable = false
        action = new Action("Exit (Esc)") {
          override def apply(): Unit =
            if (Dialog.showConfirmation(null,
              "Do you really want to quit?",
              optionType = Dialog.Options.YesNo) == Dialog.Result.Ok)
              close()
        }
      } -> East

      // Repeat Button
      layout += new UndecoratedButton(60) {
        focusable = false
        action = new Action("Repeat (R)") {
          override def apply(): Unit = {

          }
        }
      } -> West
    } -> North

    // Main Content
    layout += new BorderPanel {
      layout += new GridPanel(1, 2) {
        // Properties
        opaque = false

        // Sound Panel
        contents += new BorderPanel {
          // Parameters
          opaque = false

          // Play Sound Button
          layout += new UndecoratedButton(10) {
            focusable = false
            action = new Action("") {
              override def apply(): Unit = {
              }
            }
          } -> Center

          layout += new UndecoratedButton(50) {
            action = new Action("Next (Space)") {
              override def apply(): Unit = {
              }
            }
          } -> South
        }

        // Note Staff Panel
        contents += new Canvas(bgColor) {
          listenTo(mouse.moves)
          listenTo(mouse.clicks)
          listenTo(mouse.wheel)
        }
      } -> Center
      opaque = false
    } -> Center

    listenTo(keys)
    reactions += {
      case KeyPressed(_, Key.Escape, _, _) =>
        close()
      case KeyPressed(_, Key.F11, _, _) =>
        if (!maximized)
          maximize()
        else
          unmaximize()
    }
    focusable = true
    requestFocus
  }

  override def close(): Unit = {
    serverApi.close()
    super.close()
    sys.exit(0)
  }

  // Methods
  private def Reposition(p: Point): Unit = {
    location = new Point(location.x + p.x - clickLocation.x, location.y + p.y - clickLocation.y)
  }
}