package jp.co.hoge.apps.util

object Dump {
  def dump[T: Manifest](t: T) =  "%s: %s".format(t, manifest[T])
}
