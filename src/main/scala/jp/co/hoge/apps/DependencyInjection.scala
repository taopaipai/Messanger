package jp.co.hoge.apps

import jp.co.hoge.apps.view.MainView
import jp.co.hoge.apps.model.SelfModel
//import jp.co.hoge.apps.model.UserModel

object DependencyInjection {
  
  val mainView      = new MainView
  val selfModel     = new SelfModel
 // val userModel     = new UserModel
  val DELIMITER     = "/"

}

