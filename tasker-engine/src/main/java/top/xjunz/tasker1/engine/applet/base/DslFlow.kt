/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.base

import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2022/11/01
 */
internal class DslFlow(private val initialTarget: Any? = null) : RootFlow() {

    override fun onPrepareApply(runtime: TaskRuntime) {
        if (initialTarget != null)
            runtime.setTarget(initialTarget)
    }
}