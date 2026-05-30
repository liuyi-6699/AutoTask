/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.bridge

import android.app.UiAutomation
import top.xjunz.tasker1.uiautomator.CoroutineGestureController
import top.xjunz.tasker1.uiautomator.CoroutineInteractionController
import top.xjunz.tasker1.uiautomator.PrivilegedGestureController
import top.xjunz.tasker1.uiautomator.PrivilegedInteractionController

/**
 * @author Mengran 2022/09/30
 */
class PrivilegedUiAutomatorBridge(uiAutomation: UiAutomation) :
    ContextUiAutomatorBridge(uiAutomation) {

    override val interactionController: CoroutineInteractionController by lazy {
        PrivilegedInteractionController(this)
    }

    override val gestureController: CoroutineGestureController by lazy {
        PrivilegedGestureController(this)
    }
}