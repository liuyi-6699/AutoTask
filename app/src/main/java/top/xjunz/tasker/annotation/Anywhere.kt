/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker.annotation

import java.lang.annotation.Inherited

/**
 * The annotation indicating that its target may be used in both local and privileged processes.
 * You should deal with these two situations carefully.
 *
 * @see Local
 * @see Privileged
 *
 * @author Mengran 2022/04/30
 */
@Inherited
@Retention(AnnotationRetention.SOURCE)
annotation class Anywhere