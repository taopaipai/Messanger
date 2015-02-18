package jp.co.hoge.apps.view.helper

import java.awt.TrayIcon
import java.awt.Image
import java.awt.Toolkit

class MassangerTrayIcon(image : Image = Toolkit.getDefaultToolkit.createImage("icon.png")) extends TrayIcon(image)
