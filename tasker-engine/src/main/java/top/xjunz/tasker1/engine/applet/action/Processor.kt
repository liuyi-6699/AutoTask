/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.engine.applet.action

import top.xjunz.tasker1.engine.applet.base.AppletResult
import top.xjunz.tasker1.engine.runtime.TaskRuntime

/**
 * @author Mengran 2022/11/21
 */
abstract class Processor<Result> : ArgumentAction() {

    abstract suspend fun process(args: Array<Any?>, runtime: TaskRuntime): Result?

    final override suspend fun doAction(args: Array<Any?>, runtime: TaskRuntime): AppletResult {
        val ret = process(args, runtime)
        return if (ret != null) AppletResult.succeeded(ret) else AppletResult.EMPTY_FAILURE
    }

}

fun <Result> createProcessor(block: suspend (arg:Array<Any?>,runtime: TaskRuntime) -> Result?): Processor<Result> {
    return object : Processor<Result>() {
        override suspend fun process(args: Array<Any?>, runtime: TaskRuntime): Result? {
            return block(args, runtime)
        }
    }
}