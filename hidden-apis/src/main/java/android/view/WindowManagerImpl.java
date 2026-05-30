/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package android.view;

import android.os.IBinder;

/**
 * @author Mengran 2023/01/03
 */
public abstract class WindowManagerImpl implements WindowManager {

    public void setDefaultToken(IBinder binder) {
        throw new RuntimeException("Stub!");
    }
}
