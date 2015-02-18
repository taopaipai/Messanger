package jp.co.hoge.apps.model

import java.net.InetAddress

class SelfModel {
  final val STATUS_USUALLY : Int = 0
  final val STATUS_AWAY : Int    = 1
  final val STATUS_BUSY : Int    = 2

  val name : String              = "anonymous"
  val status : Int               = STATUS_USUALLY
  val inet : InetAddress         = InetAddress.getLocalHost
  val port                       = 2552
  val port_health                = 2553

}
