/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.event

import android.os.Handler
import android.os.Looper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.android.asCoroutineDispatcher
import org.junit.Test
import top.xjunz.tasker1.UiAutomationRegistry
import kotlin.coroutines.CoroutineContext

/**
 * @author Mengran 2022/11/01
 */
internal class A11yEventDispatcherTest : CoroutineScope {


    @Test
    fun processAccessibilityEvent() {
        UiAutomationRegistry.getUiAutomation().setOnAccessibilityEventListener {
            println(it)
        }
    }

    override val coroutineContext: CoroutineContext by lazy {
        Handler(Looper.getMainLooper()).asCoroutineDispatcher()
    }
}