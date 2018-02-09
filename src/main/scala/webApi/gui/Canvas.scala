package webApi.gui

import java.awt.{Color, Graphics2D}

import scala.swing.Panel

class Canvas(bgColor: Color = Color.BLACK) extends Panel {
  override def paintComponent(g: Graphics2D) {
    // Start by erasing this webApi.gui.Canvas
    g.setColor(bgColor)
    g.fillRect(0, 0, size.width, size.height)

    // Main Drawing
    // TODO
  }
}