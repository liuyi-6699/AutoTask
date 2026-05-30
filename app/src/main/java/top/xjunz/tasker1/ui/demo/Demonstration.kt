/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.demo

import android.content.Context
import android.view.View

/**
 * @author Mengran 2022/12/12
 */
abstract class Demonstration(val context: Context) {

    abstract fun getView(): View

    abstract fun startDemonstration()

    abstract fun stopDemonstration()
}