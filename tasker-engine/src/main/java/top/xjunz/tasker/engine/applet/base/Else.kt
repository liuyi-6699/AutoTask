/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.engine.applet.base

import top.xjunz.tasker.engine.runtime.TaskRuntime

/**
 * @author Mengran 2022/11/03
 */
open class Else : Do() {

    // Once the previous result is success, do not execute this flow
    override var relation: Int = REL_OR

    /**
     * If its previous peer is skipped, do not execute it self.
     */
    override fun shouldBeSkipped(runtime: TaskRuntime): Boolean {
        return runtime.ifSuccessful != false
    }

    override fun onPostApply(runtime: TaskRuntime) {
        super.onPostApply(runtime)
        runtime.isSuccessful = runtime.ifSuccessful == true
    }
}