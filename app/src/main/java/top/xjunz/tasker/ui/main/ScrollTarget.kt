/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.ui.main

import androidx.recyclerview.widget.RecyclerView

/**
 * @author Mengran 2023/03/05
 */
interface ScrollTarget {
    fun getScrollTarget(): RecyclerView?
}