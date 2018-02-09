import webApi.business.MainApi
import webApi.gui.Gui
import webApi.logger.{Logger, Severity}
// Simple server

object Main extends App {
  val logger = new Logger("app_data/.log", true) {
    severity = Severity.DEBUG
    println("Main api started.")
    debug("Main api started.")
  }

  val mainApi = new MainApi(logger) {}

  val gui = new Gui(mainApi) {
    centerOnScreen()
    visible = true
  }
}
