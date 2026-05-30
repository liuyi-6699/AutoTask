/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.action

import top.xjunz.tasker1.engine.applet.action.SingleArgAction
import top.xjunz.tasker1.engine.applet.base.AppletResult
import top.xjunz.tasker1.engine.runtime.TaskRuntime
import top.xjunz.tasker1.task.applet.value.LongDuration

/**
 * @author Mengran 2023/08/17
 */
class PauseFor : SingleArgAction<Long>() {

    override suspend fun doAction(arg: Long?, runtime: TaskRuntime): AppletResult {
        val duration = checkNotNull(arg)
        runtime.attachingTask.pause(LongDuration.parse(duration).toMilliseconds())
        return AppletResult.EMPTY_SUCCESS
    }
}