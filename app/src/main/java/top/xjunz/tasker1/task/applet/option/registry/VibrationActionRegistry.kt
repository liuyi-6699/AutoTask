/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.task.applet.option.registry

import android.os.Build
import top.xjunz.tasker1.R
import top.xjunz.tasker1.ktx.foreColored
import top.xjunz.tasker1.ktx.formatSpans
import top.xjunz.tasker1.ktx.str
import top.xjunz.tasker1.task.applet.action.Vibrate
import top.xjunz.tasker1.task.applet.anno.AppletOrdinal
import top.xjunz.tasker1.task.applet.value.VariantArgType

/**
 * @author Mengran 2023/10/26
 */
class VibrationActionRegistry(id: Int) : AppletOptionRegistry(id) {

    @AppletOrdinal(0x0001)
    val vibrate = appletOption(R.string.perform_vibrate) {
        Vibrate()
    }.withValueArgument<Vibrate.VibrationWaveForm>(
        R.string.vibration_pattern,
        variantValueType = VariantArgType.TEXT_VIBRATION_PATTERN
    ).restrictApiLevel(Build.VERSION_CODES.O)
        .withSingleValueDescriber<Vibrate.VibrationWaveForm> {
            if (it.durations.size == 1) {
                R.string.format_simple_vibration_pattern.formatSpans(
                    it.durations.first().toString().foreColored(),
                    it.strengths.first().toString().foreColored(),
                )
            } else {
                R.string.custom_vibrate_pattern.str
            }
        }
}