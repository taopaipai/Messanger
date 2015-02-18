package jp.co.hoge.apps.view

import scala.beans.BeanProperty
import javax.swing.{ JFrame, JTextArea, JButton, JScrollPane, JPanel }
import java.awt.{ Dimension, Container, BorderLayout }
import jp.co.hoge.apps.view.helper.UserList
import jp.co.hoge.apps.view.helper.{ SendButton, NameTextField }
import jp.co.hoge.apps.DependencyInjection

class MainView extends View("IP Messager for Scala + Akka + Swing") {

  @BeanProperty var userList : UserList = new UserList
  @BeanProperty var message : JTextArea = new JTextArea
  @BeanProperty var sendButton : SendButton = new SendButton
  @BeanProperty var myName : NameTextField = new NameTextField("anounymous")

  var panelForArea : JPanel = new JPanel
  var panelForBtn  : JPanel = new JPanel 

  message.setLineWrap(true)
  
  var scroll4Message : JScrollPane = new JScrollPane(message)
  var scroll4UserList : JScrollPane = new JScrollPane
  
  scroll4UserList.getViewport.setView(userList)

  scroll4Message.setPreferredSize(new Dimension(250, 50))
  scroll4UserList.setPreferredSize(new Dimension(250, 50))

  panelForArea.add(myName)
  panelForArea.add(scroll4UserList)
  panelForArea.add(scroll4Message)
  
  panelForBtn.add(sendButton)

  var container : Container = getContentPane
  container.add(panelForArea, BorderLayout.CENTER)
  container.add(panelForBtn, BorderLayout.SOUTH)

  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
}
