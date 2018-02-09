package webApi.business.server

import java.io.PrintStream
import java.net.ServerSocket

import webApi.logger.Logger

import scala.io.BufferedSource

class Server(port: Int, val logger: Logger) extends Runnable {
  var callback: (Any *) => Unit = (any) => {}
  var serverOpen = true
  private var _output: PrintStream = _

  // Entry point for server
  override def run(): Unit = {
    var server: ServerSocket = null
    try {
      server = new ServerSocket(port)

      while (serverOpen) {
        val s = server.accept()
        val in = new BufferedSource(s.getInputStream).getLines()
        _output = new PrintStream(s.getOutputStream)

        // read data
        val data = in.next()
        //        logger.debug(s"Got data: $data")

        // send immediate response
        _output.println(s"Request: '$data'. OK")
        _output.flush()
        s.close()

        // callback
        callback(data)
      }
    }
    catch {
      case e: Exception => {
        //        logger.error("Encountered error in server thread: " + e.getMessage)
        println(e.getMessage)
        return
      }
    }
  }

  def output: PrintStream = {
    if (_output == null) {
      throw new Exception("Output stream has not been initialized")
    }
    _output
  }
}
