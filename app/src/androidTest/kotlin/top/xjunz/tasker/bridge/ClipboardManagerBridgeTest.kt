/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.bridge

import org.junit.Test

/**
 * @author Mengran 2022/11/16
 */
internal class ClipboardManagerBridgeTest {

    @Test
    fun copyToClipboard() {
        ClipboardManagerBridge.copyToClipboard("hello")
    }
}