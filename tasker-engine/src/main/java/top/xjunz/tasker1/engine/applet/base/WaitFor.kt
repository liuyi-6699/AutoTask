/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.base

import android.os.SystemClock
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import top.xjunz.shared.ktx.casted
import top.xjunz.tasker1.engine.runtime.TaskRuntime
import java.lang.ref.WeakReference

/**
 * Wait for a certain event to occur.
 *
 * @author Mengran 2023/02/11
 */
class WaitFor : If() {

    override val requiredIndex: Int = -1

    private var waitingJob: WeakReference<Job>? = null

    override val defaultValue: Int = 5_000

    private val timeout by lazy {
        values[0]?.casted<Int>() ?: defaultValue
    }

    fun remind() {
        waitingJob?.get()?.cancel()
    }

    override suspend fun applyFlow(runtime: TaskRuntime): AppletResult {
        var elapsed = 0L
        runtime.waitingFor = this
        while (elapsed < timeout) {
            val start = SystemClock.uptimeMillis()
            coroutineScope {
                val deferred = async(start = CoroutineStart.LAZY) {
                    delay(timeout - elapsed)
                }
                waitingJob = WeakReference(deferred)
                deferred.join()
            }
            elapsed += SystemClock.uptimeMillis() - start
            if (super.applyFlow(runtime).isSuccessful) {
                return AppletResult.EMPTY_SUCCESS
            }
        }
        return AppletResult.EMPTY_FAILURE
    }
}