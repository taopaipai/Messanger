package jp.co.hoge.apps.view

import scala.beans.BeanProperty
import javax.swing.{ JTextArea, JButton, JScrollPane, JPanel }
import java.awt.{ Dimension, Container, BorderLayout }
import jp.co.hoge.apps.model.bean.Message
import java.net.InetAddress
import jp.co.hoge.apps.view.helper.SendButton
import jp.co.hoge.apps.view.helper.CloseButton

class MessageView extends View("Message") {

  @BeanProperty var message : JTextArea = new JTextArea
  @BeanProperty var fromMessage : JTextArea = new JTextArea
  @BeanProperty var sendButton : SendButton = new SendButton

  message.setLineWrap(true)
  fromMessage.setLineWrap(true)
  fromMessage.setEditable(false)

  var panelForArea : JPanel = new JPanel
  var panelForBtn  : JPanel = new JPanel

  var scroll4Message : JScrollPane = new JScrollPane(message)
  var scroll4From : JScrollPane = new JScrollPane(fromMessage)
  scroll4Message.setPreferredSize(new Dimension(250, 100))
  scroll4From.setPreferredSize(new Dimension(250, 100))
  panelForArea.add(scroll4From)
  panelForArea.add(scroll4Message)
  panelForBtn.add(sendButton)

  var container : Container = getContentPane
  container.add(panelForArea, BorderLayout.CENTER)
  container.add(panelForBtn, BorderLayout.SOUTH)
}

