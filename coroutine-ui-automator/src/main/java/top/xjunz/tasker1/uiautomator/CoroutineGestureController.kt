/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.uiautomator

import androidx.test.uiautomator.PointerGesture

/**
 * @author Mengran 2023/02/15
 */
abstract class CoroutineGestureController internal constructor(val bridge: CoroutineUiAutomatorBridge) {

    abstract suspend fun performSinglePointerGesture(gesture: PointerGesture): Boolean

}