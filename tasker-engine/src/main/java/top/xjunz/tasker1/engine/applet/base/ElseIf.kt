/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.base

import top.xjunz.shared.utils.unsupportedOperation
import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2022/11/03
 */
class ElseIf : If() {

    override var relation: Int = REL_OR
        set(value) {
            if (value != REL_OR) unsupportedOperation("ElseIf flow must not have its [isAnd] field true!")
            field = value
        }

    override fun staticCheckMyself(): Int {
        if (requireParent().getOrNull(index - 1) !is Do) {
            return StaticError.ERR_ELSEIF_NOT_FOLLOWING_DO
        }
        return super.staticCheckMyself()
    }

    /**
     * If its previous peer is skipped, do not execute it self.
     */
    override fun shouldBeSkipped(runtime: TaskRuntime): Boolean {
        return runtime.ifSuccessful != false
    }
}