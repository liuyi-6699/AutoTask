/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.option.descriptor

import androidx.annotation.StringRes
import top.xjunz.tasker1.ktx.text

/**
 * @author Mengran 2022/11/20
 */
open class ValueDescriptor(
    @StringRes
    protected val nameRes: Int,
    val valueType: Class<*>,
    val variantValueType: Int,
    val isCollection: Boolean,
) {

    val isAnonymous: Boolean get() = nameRes == -1

    val name: CharSequence get() = nameRes.text

    fun parseValueFromInput(str: String): Any? {
        if (valueType == String::class.java)
            return str

        if (valueType == Int::class.java || valueType == Int::class.javaObjectType)
            return str.toIntOrNull()

        if (valueType == Long::class.java || valueType == Long::class.javaObjectType)
            return str.toLongOrNull()

        if (valueType == Float::class.java || valueType == Float::class.javaObjectType)
            return str.toFloatOrNull()

        return null
    }
}