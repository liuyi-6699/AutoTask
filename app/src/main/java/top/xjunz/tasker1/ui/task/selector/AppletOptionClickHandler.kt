/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.selector

import androidx.fragment.app.FragmentManager
import top.xjunz.tasker1.engine.applet.base.Applet
import top.xjunz.tasker1.engine.applet.criterion.Criterion
import top.xjunz.tasker1.engine.applet.util.isAttached
import top.xjunz.tasker1.ktx.show
import top.xjunz.tasker1.task.applet.option.AppletOption
import top.xjunz.tasker1.task.applet.option.AppletOptionFactory
import top.xjunz.tasker1.ui.task.selector.argument.ArgumentsEditorDialog

/**
 * @author Mengran 2022/10/08
 */
open class AppletOptionClickHandler(private val fragmentManager: FragmentManager) {

    private val factory = AppletOptionFactory

    fun onClick(applet: Applet, onCompleted: () -> Unit) {
        onClick(applet, factory.requireOption(applet), onCompleted)
    }

    open fun onClick(applet: Applet, option: AppletOption, onCompleted: () -> Unit) {
        when {
            option.arguments.isNotEmpty() -> ArgumentsEditorDialog()
                .setAppletOption(applet, option)
                .doOnCompletion(onCompleted).show(fragmentManager)

            applet is Criterion<*, *> -> {
                if (applet.isAttached && applet.isInvertible) applet.toggleInversion()
                onCompleted()
            }

            else -> onCompleted()
        }
    }
}