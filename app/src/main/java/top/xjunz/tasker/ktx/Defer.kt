/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.ktx

import kotlinx.coroutines.Deferred

/**
 * @author Mengran 2022/12/22
 */
inline fun Deferred<*>.invokeOnError(crossinline block: (Throwable) -> Unit) {
    invokeOnCompletion {
        if (it != null) block(it)
    }
}