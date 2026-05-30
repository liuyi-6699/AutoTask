/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package android.accessibilityservice;

import android.graphics.Region;
import android.os.IBinder;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;

import dev.rikka.tools.refine.RefineAs;

/**
 * @author Mengran 2021/9/19
 */
@RefineAs(AccessibilityService.class)
public class AccessibilityServiceHidden {
    /**
     * Interface used by IAccessibilityServiceWrapper to call the service from its main thread.
     */
    public interface Callbacks {
        void onAccessibilityEvent(AccessibilityEvent event);

        void onInterrupt();

        void onServiceConnected();

        void init(int connectionId, IBinder windowToken);

        boolean onGesture(int gestureId);

        boolean onKeyEvent(KeyEvent event);

        /**
         * Magnification changed callbacks for different displays
         */
        void onMagnificationChanged(int displayId, Region region,
                                    float scale, float centerX, float centerY);

        void onSoftKeyboardShowModeChanged(int showMode);

        void onPerformGestureResult(int sequence, boolean completedSuccessfully);

        void onFingerprintCapturingGesturesChanged(boolean active);

        void onFingerprintGesture(int gesture);

        void onAccessibilityButtonClicked();

        void onAccessibilityButtonAvailabilityChanged(boolean available);
    }
}
