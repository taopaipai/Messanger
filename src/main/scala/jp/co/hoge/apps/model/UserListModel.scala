package jp.co.hoge.apps.model

import javax.swing.ListModel
import java.net.InetAddress
import jp.co.hoge.apps.model.bean.User

class UserListModel extends ListModel[User] {
  var map : Map[String, User] = Map[String, User]()

  override def getSize : Int = {
    map.size
  }
  override def getElementAt(index : Int) = {
    var key : String = ""
    var count : Int = -1
    var it = map.keysIterator

    while(it.hasNext && count < index) {
      key = it.next
      count += 1
    }
    map.get(key).get
  }
  override def addListDataListener(listener : javax.swing.event.ListDataListener) = {}
  override def removeListDataListener(listener : javax.swing.event.ListDataListener) = {}

  def addElement(user : User) = {
    map+=((user.getInetAddress.getHostAddress, user))
  }
  def removeElement(user : User) = {
    map-=(user.getInetAddress.getHostAddress)
  }
  def contains(user : User) : Boolean = {
    map.contains(user.getInetAddress.getHostAddress)
  }

}


