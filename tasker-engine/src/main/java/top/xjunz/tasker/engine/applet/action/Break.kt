/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.engine.applet.action

import top.xjunz.tasker.engine.applet.base.AppletResult
import top.xjunz.tasker.engine.runtime.TaskRuntime

/**
 * @author Mengran 2023/01/15
 */
class Break : ArgumentAction() {

    override suspend fun doAction(args: Array<Any?>, runtime: TaskRuntime): AppletResult {
        runtime.currentLoop?.shouldBreak = true
        return AppletResult.EMPTY_SUCCESS
    }
}