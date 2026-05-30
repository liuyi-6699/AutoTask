/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.flow

import android.view.accessibility.AccessibilityNodeInfo
import top.xjunz.tasker1.engine.applet.base.AppletResult
import top.xjunz.tasker1.engine.applet.base.ScopeFlow
import top.xjunz.tasker1.engine.runtime.TaskRuntime
import top.xjunz.tasker1.ktx.ensureRefresh

/**
 * @author Mengran 2023/03/13
 */
class UiObjectMatches : ScopeFlow<UiObjectTarget>() {

    override fun initializeTarget(runtime: TaskRuntime): UiObjectTarget {
        return UiObjectTarget()
    }

    override suspend fun applyFlow(runtime: TaskRuntime): AppletResult {
        val referentNode = runtime.getReferenceArgument(this, 0) as AccessibilityNodeInfo
        referentNode.ensureRefresh()
        runtime.target.source = referentNode
        return super.applyFlow(runtime)
    }
}