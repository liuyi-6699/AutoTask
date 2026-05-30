/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.event

import android.content.ClipData
import android.content.ClipboardManager
import top.xjunz.tasker1.bridge.ContextBridge
import top.xjunz.tasker1.engine.runtime.Event
import top.xjunz.tasker1.engine.task.EventDispatcher

/**
 * @author Mengran 2023/03/12
 */
class ClipboardEventDispatcher : EventDispatcher(), ClipboardManager.OnPrimaryClipChangedListener {

    companion object {
        const val EXTRA_PRIMARY_CLIP_TEXT = 0
    }

    private val clipboardManager by lazy {
        ContextBridge.getContext().getSystemService(ClipboardManager::class.java)
    }

    private var previousText: String? = null

    init {
        clipboardManager.addPrimaryClipChangedListener(this)
    }

    override fun destroy() {
        clipboardManager.removePrimaryClipChangedListener(this)
    }

    override fun onRegistered() {

    }

    private fun ClipData.getOrNull(index: Int): ClipData.Item? {
        if (itemCount - 1 >= index) {
            return getItemAt(index)
        }
        return null
    }

    override fun onPrimaryClipChanged() {
        val currentText = clipboardManager.primaryClip?.getOrNull(0)
            ?.coerceToText(ContextBridge.getContext())?.toString()
        if (previousText != currentText && currentText != null) {
            dispatchEvents(Event.obtain(Event.EVENT_ON_PRIMARY_CLIP_CHANGED).apply {
                putExtra(EXTRA_PRIMARY_CLIP_TEXT, currentText)
            })
            previousText = currentText
        }
    }

}