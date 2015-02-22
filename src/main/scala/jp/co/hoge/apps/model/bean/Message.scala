package jp.co.hoge.apps.model.bean

import java.io._
import java.net.InetAddress
import scala.beans.BeanProperty

@SerialVersionUID(123L)
class Message(@BeanProperty var message : String, @BeanProperty var to : User, @BeanProperty var from : User = new User) extends Serializable {

  override def toString : String = s"from[${from.toString}] to[${to.toString}] message[${message}]"

}
