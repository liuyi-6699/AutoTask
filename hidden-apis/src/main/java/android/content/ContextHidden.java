/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package android.content;

import android.os.UserHandle;

import dev.rikka.tools.refine.RefineAs;

/**
 * @author Mengran 2023/01/03
 */
@RefineAs(Context.class)
public class ContextHidden {

    public Context createPackageContextAsUser(String packageName, int flags, UserHandle user) {
        throw new RuntimeException("Stub!");
    }
}
