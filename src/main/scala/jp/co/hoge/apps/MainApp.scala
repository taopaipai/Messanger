package jp.co.hoge.apps

import akka.actor._
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits._
import jp.co.hoge.apps.controller._

object MainApp extends App {
  val system = ActorSystem("Messanger")
  val ctrl = system.actorOf(Props(new MainController))
  val receiver = system.actorOf(Props(new ReceiveController))
  val greeting = system.actorOf(Props(new GreetingController))
  ctrl ! "exec"
  system.scheduler.schedule(0 second, 0 second, receiver, "exec")
  system.scheduler.schedule(0 second, 5 second, greeting, "exec")
}

