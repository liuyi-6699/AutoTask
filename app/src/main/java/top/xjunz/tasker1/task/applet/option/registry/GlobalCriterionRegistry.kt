/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.option.registry

import top.xjunz.tasker1.R
import top.xjunz.tasker1.bridge.BatteryManagerBridge
import top.xjunz.tasker1.bridge.DisplayManagerBridge
import top.xjunz.tasker1.engine.applet.criterion.booleanCriterion
import top.xjunz.tasker1.ktx.format
import top.xjunz.tasker1.task.applet.anno.AppletOrdinal
import top.xjunz.tasker1.task.applet.criterion.NumberRangeCriterion.Companion.simpleNumberRangeCriterion
import top.xjunz.tasker1.task.applet.value.VariantArgType

/**
 * @author Mengran 2022/11/10
 */
class GlobalCriterionRegistry(id: Int) : AppletOptionRegistry(id) {

    @AppletOrdinal(0x0000)
    val isScreenPortrait = invertibleAppletOption(R.string.screen_orientation_portrait) {
        booleanCriterion {
            val realSize = DisplayManagerBridge.size
            realSize.x < realSize.y
        }
    }

    @AppletOrdinal(0x0010)
    val isBatteryCharging = invertibleAppletOption(R.string.is_charging) {
        booleanCriterion {
            BatteryManagerBridge.isCharging
        }
    }

    @AppletOrdinal(0x0011)
    val batteryCapacityRange = invertibleAppletOption(R.string.in_battery_capacity_range) {
        simpleNumberRangeCriterion {
            BatteryManagerBridge.capacity
        }
    }.withValueArgument<Int>(
        R.string.in_battery_capacity_range, VariantArgType.INT_PERCENT, true
    ).withSingleValueDescriber<Collection<Int?>> {
        val first = it.firstOrNull()
        val last = it.lastOrNull()
        if (first == null && last != null) {
            R.string.format_percent_less_than.format(last)
        } else if (last == null && first != null) {
            R.string.format_percent_larger_than.format(first)
        } else if (last == first) {
            "$first%"
        } else {
            R.string.format_percent_range.format(first, last)
        }
    }

}