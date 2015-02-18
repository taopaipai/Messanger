package jp.co.hoge.apps.controller

import java.net._
import akka.actor._
import jp.co.hoge.apps.view.MessageView
import jp.co.hoge.apps.model.MessageModel
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.model.SelfModel
import jp.co.hoge.apps.view.listener.{ ViewEventListener, ViewWindowEventListener }
import jp.co.hoge.apps.DependencyInjection

class MessageController(args: Array[String]) extends Controller(args) {

  var view : MessageView = new MessageView
  var model : MessageModel = new MessageModel

  val OPTION_INET_ADDRESS : Int = 0
  val OPTION_NAME : Int         = 1 
  val OPTION_MESSAGE : Int      = 2 

  override def setUp = {
    view.getFromMessage.setText(s"This Message from ${args(OPTION_INET_ADDRESS)}${DependencyInjection.DELIMITER}${args(OPTION_NAME)}\n------\n${args(OPTION_MESSAGE)}")
    view.getSendButton.addActionListener(
      new ViewEventListener(
        (event : java.awt.event.ActionEvent) => {
          view.setVisible(false)
          model.send(new Message(InetAddress.getByName(args(OPTION_INET_ADDRESS)), view.getMessage.getText, DependencyInjection.selfModel.port, DependencyInjection.mainView.getMyName.getText))
          context.stop(self)
        }
      )
    )
    view.addWindowListener(
      new ViewWindowEventListener(
        (event : java.awt.event.WindowEvent) => {
          if (event.getID == java.awt.event.WindowEvent.WINDOW_CLOSED) {
            Option(context) match {
              case Some(x) => context.stop(self)
              case None    =>
            }
          }
        }
      )
    )
  }
  override def run = {
  }
  override def tearDown = {
    view.setVisible(true)
  }
}
