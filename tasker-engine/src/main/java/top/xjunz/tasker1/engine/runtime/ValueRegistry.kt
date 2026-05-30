/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.runtime

import android.util.ArrayMap
import top.xjunz.shared.ktx.casted
import java.util.*

/**
 * @author Mengran 2023/02/16
 */
open class ValueRegistry {

    class WeakKey

    private var weakRegistry: MutableMap<WeakKey, Any>? = null

    private var registry: MutableMap<Any, Any>? = null

    @Synchronized
    fun requireRegistry(): MutableMap<Any, Any> {
        if (registry == null) {
            registry = Collections.synchronizedMap(ArrayMap())
        }
        return registry!!
    }

    @Synchronized
    fun requireWeakRegistry(): MutableMap<WeakKey, Any> {
        if (weakRegistry == null) {
            weakRegistry = Collections.synchronizedMap(WeakHashMap<WeakKey, Any>())
        }
        return weakRegistry!!
    }

    @Synchronized
    fun <V : Any> getWeakValue(key: WeakKey, initializer: () -> V): V {
        return requireWeakRegistry().getOrPut(key, initializer).casted()
    }

    @Synchronized
    fun <V : Any> getValue(key: Any, initializer: () -> V): V {
        return requireRegistry().getOrPut(key, initializer).casted()
    }

    fun clearValues() {
        registry = null
        weakRegistry = null
    }
}