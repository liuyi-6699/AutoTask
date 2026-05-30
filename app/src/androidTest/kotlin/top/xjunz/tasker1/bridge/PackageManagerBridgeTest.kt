/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.bridge

import org.junit.Test
import top.xjunz.tasker1.BuildConfig


/**
 * @author Mengran 2023/01/06
 */
internal class PackageManagerBridgeTest {

    @Test
    fun loadPackageInfo() {
        val info = PackageManagerBridge.loadPackageInfo(BuildConfig.APPLICATION_ID)
        assert(info!!.packageName == BuildConfig.APPLICATION_ID)
    }

    @Test
    fun loadAllPackages() {
    }
}