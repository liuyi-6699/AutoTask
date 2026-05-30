/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.task

import androidx.collection.ArraySet
import top.xjunz.shared.ktx.casted
import top.xjunz.tasker1.engine.runtime.Event

/**
 * @author Mengran 2022/12/04
 */
abstract class EventDispatcher {

    private val callbacks = ArraySet<Callback>()

    abstract fun destroy()

    abstract fun onRegistered()

    open fun addCallback(callback: Callback) {
        callbacks.add(callback)
    }

    open fun removeCallback(callback: Callback) {
        callbacks.remove(callback)
    }

    fun dispatchEvents(vararg events: Event) {
        callbacks.forEach {
            it.onEvents(events.casted())
        }
    }

    fun interface Callback {

        fun onEvents(events: Array<Event>)

    }

}