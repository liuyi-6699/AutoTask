/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.ui.base

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

/**
 * An [AbstractSavedStateViewModelFactory] which supports initiating an inner-class [ViewModel].
 *
 * @author Mengran 2022/05/08
 */
object InnerViewModelFactory : AbstractSavedStateViewModelFactory() {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        runCatching {
            val constructor = modelClass.getDeclaredConstructor()
            constructor.isAccessible = true
            return constructor.newInstance()
        }.onFailure {
            val constructor = modelClass.getDeclaredConstructor(SavedStateHandle::class.java)
            constructor.isAccessible = true
            return constructor.newInstance(handle)
        }
        throw RuntimeException("No suitable constructor!")
    }
}
