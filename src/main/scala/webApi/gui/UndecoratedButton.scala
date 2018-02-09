package webApi.gui

import java.awt.Font

import scala.swing.Button

class UndecoratedButton(fontSize: Int = 12) extends Button {
  font = new Font("Times New Roman", Font.BOLD, fontSize)
  contentAreaFilled = false
  borderPainted = false
  focusPainted = false
}