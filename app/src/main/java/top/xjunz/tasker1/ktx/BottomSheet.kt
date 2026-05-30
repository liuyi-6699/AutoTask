/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ktx

import android.annotation.SuppressLint
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior

/**
 * @author Mengran 2022/10/03
 */
fun View.requireBottomSheetBehavior(): BottomSheetBehavior<*> {
    return (layoutParams as CoordinatorLayout.LayoutParams).behavior as BottomSheetBehavior<*>
}

@SuppressLint("RestrictedApi", "VisibleForTests")
fun View.disableBottomSheetShapeAnimation(): BottomSheetBehavior<*> {
    val behavior = requireBottomSheetBehavior()
    behavior.disableShapeAnimations()
    return behavior
}

fun View.getBottomSheetState(): Int {
    return requireBottomSheetBehavior().state
}