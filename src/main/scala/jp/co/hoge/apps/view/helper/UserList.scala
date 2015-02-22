package jp.co.hoge.apps.view.helper

import javax.swing.{ JList }
import jp.co.hoge.apps.model.UserListModel
import jp.co.hoge.apps.model.bean.User

class UserList extends JList(new UserListModel)
