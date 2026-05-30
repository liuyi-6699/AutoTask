/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.value

/**
 * @author Mengran 2023/01/08
 */
abstract class ValueComposer<Component, Value> {

    fun compose(vararg components: Component?): Value {
        return composeInternal(components)
    }

    protected abstract fun composeInternal(components: Array<out Component?>): Value

    abstract fun parse(composed: Value): Array<Component?>
}