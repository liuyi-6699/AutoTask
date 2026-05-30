/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.engine.applet.action

import top.xjunz.tasker.engine.applet.base.Applet

/**
 * @author Mengran 2023/09/24
 */
abstract class Action : Applet() {

    final override val supportsAnywayRelation: Boolean = true

}
