/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.engine.applet.base

/**
 * @author Mengran 2022/11/04
 */
abstract class ControlFlow : Flow() {

    override var relation: Int = REL_ANYWAY
        set(value) {
            check(value == REL_ANYWAY)
            field = REL_ANYWAY
        }

    final override var isInvertible: Boolean = false
}