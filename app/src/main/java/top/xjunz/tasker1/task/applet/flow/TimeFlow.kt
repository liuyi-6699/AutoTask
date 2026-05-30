/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.flow

import top.xjunz.tasker1.engine.applet.base.ScopeFlow
import top.xjunz.tasker1.engine.runtime.Event
import top.xjunz.tasker1.engine.runtime.TaskRuntime
import top.xjunz.tasker1.task.event.PollEventDispatcher
import java.util.Calendar

/**
 * @author Mengran 2022/10/01
 */
class TimeFlow : ScopeFlow<Calendar>() {

    override fun initializeTarget(runtime: TaskRuntime): Calendar {
        val calendar = Calendar.getInstance()
        val current = runtime.events?.find { it.type == Event.EVENT_ON_TICK }
            ?.getExtra<Long>(PollEventDispatcher.EXTRA_TICK_TIME_MILLS)
            ?: System.currentTimeMillis()
        // Prune milliseconds
        calendar.timeInMillis = current - current % 1000
        return calendar
    }
}