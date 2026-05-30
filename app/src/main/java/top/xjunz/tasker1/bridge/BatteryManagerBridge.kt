/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.bridge

import android.os.BatteryManager

/**
 * @author Mengran 2022/11/10
 */
object BatteryManagerBridge {

    private val batteryManager: BatteryManager by lazy {
        ContextBridge.getContext().getSystemService(BatteryManager::class.java)
    }

    val isCharging: Boolean get() = batteryManager.isCharging

    val capacity: Int
        get() {
            return batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
        }
}