package jp.co.hoge.apps.controller

import jp.co.hoge.apps.model.MessageModel
import jp.co.hoge.apps.model.SelfModel
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.model.bean.User
import java.net._
import scala.util.control.Breaks.{ break, breakable }
import jp.co.hoge.apps.DependencyInjection

class GreetingController extends Controller(Array()) {

  var model : MessageModel = new MessageModel

  override def setUp = {
  }

  override def run = {
    var message = new Message("", new User(broadcast, "all"), new User)
    logger.info(message.toString)
    model.send(message)
  }

  override def tearDown = {
  }

  def broadcast : InetAddress = {
    var ret = InetAddress.getByName("255.255.255.255")
    var it = NetworkInterface.getByInetAddress(InetAddress.getLocalHost).getInterfaceAddresses.iterator
    breakable {
      while(it.hasNext) {
        var inet = it.next.getBroadcast 
        inet match {
          case _ : InetAddress => ret = inet; break
          case _               =>
        }
      }
    }
    ret
  }
}
