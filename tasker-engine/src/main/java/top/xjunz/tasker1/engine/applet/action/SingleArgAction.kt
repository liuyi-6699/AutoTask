/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.action

import top.xjunz.shared.ktx.casted
import top.xjunz.tasker1.engine.applet.base.AppletResult
import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2023/09/23
 */
abstract class SingleArgAction<Arg> : ArgumentAction() {

    final override suspend fun doAction(args: Array<Any?>, runtime: TaskRuntime): AppletResult {
        return doAction(args.single()?.casted(), runtime)
    }

    abstract suspend fun doAction(arg: Arg?, runtime: TaskRuntime): AppletResult
}

fun <Arg> singleArgAction(block: suspend (Arg?) -> AppletResult): Action {
    return object : SingleArgAction<Arg>() {
        override suspend fun doAction(arg: Arg?, runtime: TaskRuntime): AppletResult {
            return block(arg)
        }
    }
}

fun <Arg> simpleSingleArgAction(block: suspend (Arg?) -> Boolean): Action {
    return singleArgAction<Arg> { AppletResult.emptyResult(block(it)) }
}

fun <Arg> simpleSingleNonNullArgAction(block: suspend (Arg) -> Boolean): Action {
    return simpleSingleArgAction<Arg> {
        block(it!!)
    }
}