/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package x;

/**
 * @author Mengran 2023/03/01
 */
public class f /* Security */ {

    static {
        System.loadLibrary("ssl");
    }

    public static native String alpha(byte[] bytes); // encrypt

    public static native byte[] delta(String str); // decrypt
}
