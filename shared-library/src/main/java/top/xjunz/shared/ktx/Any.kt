/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.shared.ktx

/**
 * @author Mengran 2022/07/14
 */

@Throws(ClassCastException::class)
@Suppress("UNCHECKED_CAST")
fun <T> Any.casted(): T {
    return this as T
}