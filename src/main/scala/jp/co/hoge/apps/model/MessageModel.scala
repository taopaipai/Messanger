package jp.co.hoge.apps.model

import scala.beans.BeanProperty
import java.net._
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream 
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream
import jp.co.hoge.apps.model.bean.Message
import jp.co.hoge.apps.model.bean.User
import jp.co.hoge.apps.model.listener.MessageEventListener
import jp.co.hoge.apps.DependencyInjection

class MessageModel {
  
  final val SIZE_MAX_PACKET = 1024

  @BeanProperty var listener : MessageEventListener = _

  def using[A, R <: { def close() }](r : R)(f : R => A) : A =
    try {
      f(r)
    } catch {
      case e : Throwable => throw e
    } finally {
      r.close()
    }

  def send(message : Message) = {
    using(new ByteArrayOutputStream(SIZE_MAX_PACKET)) { baos =>
      using(new ObjectOutputStream(baos)) { oos =>
        oos.writeObject(message)
        using(new DatagramSocket) { socket =>
          var packet  = new DatagramPacket(baos.toByteArray, baos.toByteArray.length, message.getTo.getInetAddress, message.getTo.getPort)
          socket.send(packet)
        }
      }
    }
  }

  def receive : Message = {
    var packet : DatagramPacket = new DatagramPacket(new Array[Byte](SIZE_MAX_PACKET), SIZE_MAX_PACKET)
    using(new DatagramSocket(DependencyInjection.mainView.getPort.getText.toInt)) { socket => 
      socket.receive(packet)
    }
    using(new ByteArrayInputStream(packet.getData)) { bais => 
      using(new ObjectInputStream(bais)) { ois => 
        ois.readObject.asInstanceOf[Message]
      }
    }
  }

}
