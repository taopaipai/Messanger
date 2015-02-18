package jp.co.hoge.apps.view.listener

import scala.beans.BeanProperty
import akka.actor.{ ActorSystem, Props }
import java.awt.event.{ WindowListener, WindowEvent }

class ViewWindowEventListener(f: WindowEvent => Unit) extends WindowListener {

  override def windowActivated(event : WindowEvent) = {f(event)}
  override def windowClosing(event : WindowEvent) = {f(event)}
  override def windowDeactivated(event : WindowEvent) = {f(event)} 
  override def windowDeiconified(event : WindowEvent) = {f(event)} 
  override def windowIconified(event : WindowEvent) = {f(event)} 
  override def windowOpened(event : WindowEvent) = {f(event)} 
  override def windowClosed (event : WindowEvent) = {f(event)}
}
