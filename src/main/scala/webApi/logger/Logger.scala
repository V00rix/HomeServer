package webApi.logger

import java.io.{File, FileWriter}
import java.util.Calendar

import webApi.logger.Severity.Severity

class Logger(filePath: String, append: Boolean = true, var severity: Severity = Severity.DEBUG) {
  private val pw = new FileWriter(new File(filePath), append)

  def close(): Unit = {
    pw.close()
  }

  def debug(line: String): Unit = {
    if (severity == Severity.DEBUG) {
      write(s"${Calendar.getInstance().getTime}: $line")
    }
  }

  private def write(line: String): Unit = {
    println(s"$line\n")
  }

  def info(line: String): Unit = {
    if (severity != Severity.ERROR) {
      write(s"${Calendar.getInstance().getTime}: $line")
    }
  }

  def error(line: String): Unit = {
    write(s"${Calendar.getInstance().getTime}: $line")
  }
}
