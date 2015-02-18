package jp.co.hoge.apps.controller

import scala.util.control.Breaks.{ break, breakable }

import jp.co.hoge.apps.DependencyInjection
import java.net._
import jp.co.hoge.apps.model.MessageModel
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.view.listener.{ ViewWindowEventListener, ViewEventListener }

class MainController extends Controller(Array()) {

  var model = new MessageModel 

  override def setUp = {
    DependencyInjection.mainView.getSendButton.addActionListener(
      new ViewEventListener(
        (event : java.awt.event.ActionEvent) => {
          // TODO : UNCODE...
          for (selected <- DependencyInjection.mainView.getUserList.getSelectedValues) {
            var tmp = selected.toString.split(" -> ")
            model.send(new Message(InetAddress.getByName(tmp(0).replace("Map(", "").replace("/", "")), DependencyInjection.mainView.getMessage.getText, DependencyInjection.selfModel.port, DependencyInjection.mainView.getMyName.getText))
            DependencyInjection.mainView.getMessage.setText("")
          }
        }
      )
    )
    DependencyInjection.mainView.addWindowListener(
      new ViewWindowEventListener(
        (event : java.awt.event.WindowEvent) => {
          if (event.getID == java.awt.event.WindowEvent.WINDOW_CLOSED) {
            Option(context) match {
              case Some(x) => killProc
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
    DependencyInjection.mainView.setVisible(true)
  }
}
