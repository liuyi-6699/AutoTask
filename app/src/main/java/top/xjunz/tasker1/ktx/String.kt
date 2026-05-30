/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ktx

import org.intellij.lang.annotations.Language

/**
 * @author Mengran 2022/11/16
 */
fun String.firstGroupValue(@Language("RegExp") pattern: String): String? {
    return Regex(pattern).find(this)?.groupValues?.get(1)
}