package test

import java.io.PrintStream
import java.net.{InetAddress, Socket}

import scala.io.BufferedSource
import scala.util.Random

object TestClient extends App {

  val dataArray = Array("words", "are", "not", "generated", "randomly", "because", "this", "is a", "hand-written", "string")


  while (true) {
    val s = new Socket(InetAddress.getByName("localhost"), 9999)
    lazy val in = new BufferedSource(s.getInputStream).getLines()
    val out = new PrintStream(s.getOutputStream)


    out.println(dataArray(new Random().nextInt(dataArray.length)))
    Thread.sleep(1000)
    out.flush()

    println(s"Response: '${in.next()}'")

    //    inp = scala.io.StdIn.readLine()

    s.close()
  }
}