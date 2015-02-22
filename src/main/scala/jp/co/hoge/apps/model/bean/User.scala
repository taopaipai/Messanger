package jp.co.hoge.apps.model.bean

import java.net.InetAddress
import scala.beans.BeanProperty
import jp.co.hoge.apps.DependencyInjection

@SerialVersionUID(123L)
class User(@BeanProperty var inetAddress : InetAddress = InetAddress.getLocalHost, 
           @BeanProperty var name : String = DependencyInjection.mainView.getMyName.getText, 
           @BeanProperty var port : Int = DependencyInjection.mainView.getPort.getText.toInt) extends Serializable {
  override def toString : String = s"name=${name}, inetAddress=${inetAddress}, port=${port}"
}
