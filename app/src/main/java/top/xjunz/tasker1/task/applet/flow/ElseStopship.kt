/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.flow

import top.xjunz.tasker1.engine.applet.base.Else
import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2023/09/13
 */
class ElseStopship : Else() {

    override val maxSize: Int = 0

    override val minSize: Int = 0

    override fun onPostApply(runtime: TaskRuntime) {
        super.onPostApply(runtime)
        runtime.shouldStop = true
    }
}