/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ktx

import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.core.view.children
import androidx.core.view.doOnPreDraw
import androidx.core.view.get
import top.xjunz.shared.ktx.casted
import top.xjunz.tasker1.ui.main.ColorScheme
import top.xjunz.tasker1.util.ReflectionUtil.invokeDeclaredMethod


/**
 * @author Mengran 2022/11/09
 */

/**
 * Config the first item as menu title. Call this after [PopupMenu.show].
 */
fun PopupMenu.configHeaderTitle() {
    val list = invokeDeclaredMethod<ListView>("getMenuListView")
    list.doOnPreDraw {
        val text = list[0].casted<ViewGroup>()
            .findViewById<TextView>(com.google.android.material.R.id.title)
        text.setTextAppearance(com.google.android.material.R.style.TextAppearance_Material3_BodyMedium)
        text.setTextColor(ColorScheme.colorPrimary)
        text.isClickable = false
    }
}

fun Menu.indexOf(item: MenuItem): Int {
    return children.indexOf(item)
}