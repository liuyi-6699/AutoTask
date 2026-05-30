/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package android.os;

import dev.rikka.tools.refine.RefineAs;

/**
 * @author Mengran 2022/12/24
 */
@RefineAs(IBinder.class)
public interface IBinderHidden {
    int SHELL_COMMAND_TRANSACTION = ('_'<<24)|('C'<<16)|('M'<<8)|'D';
}
