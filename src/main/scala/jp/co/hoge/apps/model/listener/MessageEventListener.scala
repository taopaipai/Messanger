package jp.co.hoge.apps.model.listener

import jp.co.hoge.apps.model.bean.Message

abstract class MessageEventListener {
  def send(message : Message)
  def receive(message : Message)
}

