package jp.co.hoge.apps.model.bean

import java.io._
import java.net.InetAddress
import scala.beans.BeanProperty

@SerialVersionUID(123L)
class Message(@BeanProperty var inetAddress : InetAddress, @BeanProperty var message : String, @BeanProperty var port : Int, @BeanProperty var name : String) extends Serializable {
  override def toString : String = s"name=${name}, inetAddress=${inetAddress}, port=${port}, message=${message}"
}
