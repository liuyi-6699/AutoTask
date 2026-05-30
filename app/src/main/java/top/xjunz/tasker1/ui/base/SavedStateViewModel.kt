/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * @author Mengran 2022/10/25
 */
open class SavedStateViewModel(private val states: SavedStateHandle) : ViewModel() {

    fun <T> get(key: String): MutableLiveData<T> {
        return states.getLiveData(key)
    }

    fun <T> setValue(key: String, value: T?) {
        get<T>(key).value = value
    }

    class SavedStateProperty<T> : ReadOnlyProperty<SavedStateViewModel, MutableLiveData<T>> {

        override fun getValue(thisRef: SavedStateViewModel, property: KProperty<*>): MutableLiveData<T> {
            return thisRef.get(property.name)
        }
    }

    fun <T> savedLiveData(): SavedStateProperty<T> {
        return SavedStateProperty()
    }
}