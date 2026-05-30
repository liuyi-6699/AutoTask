/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.service

import android.annotation.SuppressLint
import android.os.Looper
import android.os.PowerManager.WakeLock
import top.xjunz.tasker1.bridge.OverlayToastBridge
import top.xjunz.tasker1.bridge.PowerManagerBridge
import top.xjunz.tasker1.engine.applet.base.AppletResult
import top.xjunz.tasker1.engine.runtime.TaskRuntime
import top.xjunz.tasker1.engine.task.XTask
import top.xjunz.tasker1.task.applet.flow.ref.ComponentInfoWrapper
import top.xjunz.tasker1.task.event.A11yEventDispatcher
import top.xjunz.tasker1.task.event.MetaEventDispatcher
import top.xjunz.tasker1.task.event.NetworkEventDispatcher
import top.xjunz.tasker1.task.event.PollEventDispatcher
import top.xjunz.tasker1.task.runtime.ITaskCompletionCallback
import top.xjunz.tasker1.task.runtime.OneshotTaskScheduler
import top.xjunz.tasker1.task.runtime.ResidentTaskScheduler
import top.xjunz.tasker1.uiautomator.CoroutineUiAutomatorBridge

/**
 * A service defines the common abstractions of [A11yAutomatorService] and [ShizukuAutomatorService].
 *
 * @author Mengran 2022/07/21
 */
interface AutomatorService {

    val isRunning: Boolean

    val uiAutomatorBridge: CoroutineUiAutomatorBridge

    val looper: Looper

    val eventDispatcher: MetaEventDispatcher

    val overlayToastBridge: OverlayToastBridge

    val residentTaskScheduler: ResidentTaskScheduler

    val oneshotTaskScheduler: OneshotTaskScheduler

    val a11yEventDispatcher: A11yEventDispatcher

    var wakeLock: WakeLock?

    fun getCurrentComponentInfo(): ComponentInfoWrapper {
        return a11yEventDispatcher.getCurrentComponentInfo()
    }

    fun scheduleOneshotTask(task: XTask, onCompletion: ITaskCompletionCallback)

    fun stopOneshotTask(task: XTask)

    fun suppressResidentTaskScheduler(suppress: Boolean)

    fun initEventDispatcher() {
        eventDispatcher.registerEventDispatcher(a11yEventDispatcher)
        eventDispatcher.registerEventDispatcher(PollEventDispatcher(looper))
        eventDispatcher.registerEventDispatcher(NetworkEventDispatcher())
        //eventDispatcher.registerEventDispatcher(ClipboardEventDispatcher())
        eventDispatcher.addCallback(residentTaskScheduler)
        eventDispatcher.addCallback(oneshotTaskScheduler)
    }

    fun prepareWorkerMode(acquireWakeLock: Boolean) {
        initEventDispatcher()
        if (acquireWakeLock) {
            acquireWakeLock()
        }
    }

    @SuppressLint("WakelockTimeout")
    fun acquireWakeLock() {
        wakeLock = PowerManagerBridge.obtainWakeLock()
        wakeLock?.acquire()
    }

    fun releaseWakeLock() {
        wakeLock?.release()
        wakeLock = null
    }

    fun destroy() {
        AppletResult.drainPool()
        TaskRuntime.drainPool()
        releaseWakeLock()
    }

    fun getStartTimestamp(): Long

}