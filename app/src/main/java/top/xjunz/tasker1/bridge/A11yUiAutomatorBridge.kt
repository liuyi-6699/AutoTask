/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.bridge

import android.app.UiAutomation
import top.xjunz.tasker1.service.a11yAutomatorService
import top.xjunz.tasker1.uiautomator.A11yGestureController
import top.xjunz.tasker1.uiautomator.A11yInteractionController
import top.xjunz.tasker1.uiautomator.CoroutineGestureController
import top.xjunz.tasker1.uiautomator.CoroutineInteractionController


/**
 * @author Mengran 2022/07/23
 */
class A11yUiAutomatorBridge(uiAutomation: UiAutomation) : ContextUiAutomatorBridge(uiAutomation) {

    override val interactionController: CoroutineInteractionController by lazy {
        A11yInteractionController(a11yAutomatorService, this)
    }

    override val gestureController: CoroutineGestureController by lazy {
        A11yGestureController(a11yAutomatorService, this)
    }
}