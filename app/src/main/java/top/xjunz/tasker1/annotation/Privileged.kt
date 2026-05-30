/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.annotation

/**
 * The annotation indicating that its target is only used in a privileged process. Do not use any
 * resources only available in local process such as application context.
 *
 * @author Mengran 2022/04/29
 */
@Retention(AnnotationRetention.SOURCE)
annotation class Privileged
