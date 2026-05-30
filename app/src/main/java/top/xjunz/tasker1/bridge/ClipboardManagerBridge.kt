/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.bridge

import android.content.ClipData
import android.content.ClipboardManager

/**
 * @author Mengran 2022/11/16
 */
object ClipboardManagerBridge {

    fun copyToClipboard(text: CharSequence) {
        ContextBridge.getContext().getSystemService(ClipboardManager::class.java)
            .setPrimaryClip(ClipData.newPlainText(null, text))
    }
}