package jp.co.hoge.apps.controller

import akka.actor._
import jp.co.hoge.apps.model._
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.controller._
import jp.co.hoge.apps.DependencyInjection
import javax.swing.DefaultListModel
import java.net.InetAddress
import scala.util.control.Breaks.{ break, breakable }

class ReceiveController extends Controller(Array()) {

  var model : MessageModel = new MessageModel
  var userList : DefaultListModel = new DefaultListModel

  def setUp = {
    DependencyInjection.mainView.getUserList.setModel(userList)
  }

  def run = {
    var message = model.receive
    logger.info(message toString)
    message.getMessage match {
      case "" => registUser(message) 
      case _  => replyMessage(message)
    }
  }

  def tearDown = {
  }

  def replyMessage(message : Message) = {
    val reply : ActorRef = context.actorOf(Props(new MessageController(Array(message.getInetAddress.getHostAddress, message.getName, message.getMessage))))
    reply ! "exec"
  }

  def registUser(message : Message) = {
    var user : Map[InetAddress, String] = Map(message.getInetAddress -> message.getName) 
    breakable {
      for(o <- userList.toArray) {
        if (o.asInstanceOf[Map[InetAddress, String]].contains(message.getInetAddress)) {
          userList.removeElement(o)
          break
        }
      }
    }
    if (!userList.contains(user)) {
      userList.addElement(user)
    }
  }
}
