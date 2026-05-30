/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.base

import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2022/11/03
 */
open class Do : ControlFlow() {

    override var relation: Int = REL_AND

    override fun shouldBeSkipped(runtime: TaskRuntime): Boolean {
        return runtime.ifSuccessful != true
    }
}