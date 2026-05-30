/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.factory

import top.xjunz.tasker1.engine.applet.base.Applet

/**
 * @author Mengran 2022/10/28
 */
interface AppletFactory {

    fun createAppletById(id: Int, compatMode: Boolean): Applet
}