package jp.co.hoge.apps.controller

import java.net._
import akka.actor._
import jp.co.hoge.apps.view.MessageView
import jp.co.hoge.apps.model.MessageModel
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.model.bean.User
import jp.co.hoge.apps.model.SelfModel
import jp.co.hoge.apps.view.listener.{ ViewEventListener, ViewWindowEventListener }
import jp.co.hoge.apps.DependencyInjection

class MessageController(args: Array[AnyRef]) extends Controller(args) {

  var fromMessage : Message = args(0).asInstanceOf[Message]

  var view : MessageView = new MessageView
  var model : MessageModel = new MessageModel

  override def setUp = {
    view.getFromMessage.setText(fromMessage.toString)
    view.getSendButton.addActionListener(
      new ViewEventListener(
        (event : java.awt.event.ActionEvent) => {
          view.setVisible(false)
          model.send(new Message(view.getMessage.getText, fromMessage.getFrom, new User))
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
