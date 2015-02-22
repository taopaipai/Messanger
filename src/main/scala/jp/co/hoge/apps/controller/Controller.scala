package jp.co.hoge.apps.controller

import akka.actor._
import akka.event.Logging

import jp.co.hoge.apps.exception.SystemException 
import jp.co.hoge.apps.exception.ApplicationException

import java.io._

/**
 * Controller
 */
abstract class Controller(var args: Array[AnyRef]) extends Actor {
  import context._

  final val ERROR_LEVEL_NOMAL                = 0
  final val ERROR_LEVEL_APPLICATIOIN_ERROR   = -1
  final val ERROR_LEVEL_SYSTEM_ERROR         = -2

  protected val logger = Logging(context.system, this) 

  protected var errorLevel:Int = ERROR_LEVEL_NOMAL

  def receive = {
    case _ => exec 
  }

  def exec {
    errorLevel = ERROR_LEVEL_NOMAL
    try {
      logger.info("setUp START")
      setUp
      logger.info("setUp END")

      logger.info("run START")
	  run
      logger.info("run END")
    } catch {
      case e : ApplicationException => errorLevel = ERROR_LEVEL_APPLICATIOIN_ERROR; logger.error(e, "ApplicationException", e.getMessage, "")
	  case e : SystemException      => errorLevel = ERROR_LEVEL_SYSTEM_ERROR; logger.error(e, "SystemException", e.getMessage, "") 
	  case e : Throwable            => errorLevel = ERROR_LEVEL_SYSTEM_ERROR; logger.error(e, "Throwable", e.getMessage, "") 
	} finally {
      logger.info("tearDown START")
      tearDown
      logger.info(s"tearDown END : ERROR_LEVEL=${errorLevel}")
	}
  }

  def killProc {
    println("killProc Start")
    context.system.shutdown
    println("Sleep")
    Thread.sleep(30000)
    println("Wakeup")
    System.exit(0)
    println("killProc End")
  }

  def setUp
  def run
  def tearDown

}
