package webApi.business

import webApi.business.server.Server
import webApi.logger.Logger

class MainApi(val logger: Logger) extends BaseComponent {

  // Create server.Server
  val serverPort = 9999

  val server = new Server(serverPort, logger) {
    callback = (any) => {
      if (!any.toString.isEmpty) {
        println(any)
      }
      else {
        println("Couldn't convert to non-empty string")
      }
    }
  }
  //  logger.debug("server.Server created")

  // launch server thread
  val serverThread = new Thread(server)
  serverThread.start()


  def close(): Unit = {
    logger.close()
  }
}
