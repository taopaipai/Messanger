package jp.co.hoge.apps.view.listener

import scala.beans.BeanProperty
import akka.actor.{ ActorSystem, Props }
import java.awt.event.{ ActionListener, ActionEvent }

class ViewEventListener(f: ActionEvent => Unit) extends ActionListener {
  override def actionPerformed (event : ActionEvent) = {f(event)}
}
