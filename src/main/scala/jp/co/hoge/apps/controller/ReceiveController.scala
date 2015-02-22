package jp.co.hoge.apps.controller

import akka.actor._
import jp.co.hoge.apps.model._
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.controller._
import jp.co.hoge.apps.DependencyInjection
import jp.co.hoge.apps.model.UserListModel
import java.net.InetAddress
import scala.util.control.Breaks.{ break, breakable }

class ReceiveController extends Controller(Array()) {

  var messageModel : MessageModel = new MessageModel

  def setUp = {
  }

  def run = {
    var message = messageModel.receive
    logger.info(message.toString)
    message.getMessage match {
      case "" => registUser(message) 
      case _  => replyMessage(message)
    }
  }

  def tearDown = {
  }

  def replyMessage(message : Message) = {
    val reply : ActorRef = context.actorOf(Props(new MessageController(Array(message))))
    reply ! "exec"
  }

  def registUser(message : Message) = {
    DependencyInjection.mainView.getUserList.getModel.asInstanceOf[UserListModel].addElement(message.getFrom)
  }
}
