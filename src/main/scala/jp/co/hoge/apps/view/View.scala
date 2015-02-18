package jp.co.hoge.apps.view

import javax.swing.{ JFrame, JPanel, JLabel, SwingUtilities }
import java.awt.{ Container, BorderLayout, Dimension, Color }
import java.awt.event.{ KeyListener, KeyEvent }

abstract class View(title : String, width : Int = 300, height : Int = 230) extends JFrame {

  setTitle(title)
  setSize(width, height)

  // override def show = setVisible(true)
  // override def hide = setVisible(false)

}
