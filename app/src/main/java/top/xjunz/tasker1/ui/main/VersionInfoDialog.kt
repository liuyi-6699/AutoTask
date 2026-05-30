/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.main

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import top.xjunz.tasker1.BuildConfig
import top.xjunz.tasker1.R
import top.xjunz.tasker1.app
import top.xjunz.tasker1.databinding.DialogVersionInfoBinding
import top.xjunz.tasker1.ktx.*
import top.xjunz.tasker1.ui.base.BaseDialogFragment
import top.xjunz.tasker1.ui.main.EventCenter.doOnEventRouted
import top.xjunz.tasker1.ui.main.MainViewModel.Companion.peekMainViewModel
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener
import top.xjunz.tasker1.util.Icons.myIcon

/**
 * @author Mengran 2023/02/28
 */
class VersionInfoDialog : BaseDialogFragment<DialogVersionInfoBinding>() {

    companion object {
        const val HOST_PRIVACY_POLICY = "privacy_policy"
    }

    override val isFullScreen: Boolean = false

    private var observed = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivIcon.setImageBitmap(myIcon)
        binding.tvCr.movementMethod = LinkMovementMethod.getInstance()
        binding.tvVersionName.text = BuildConfig.VERSION_NAME
        val mvm = peekMainViewModel()
        binding.btnUpdate.setNoDoubleClickListener {
            if (app.updateInfo.isNull()) {
                if (!observed) {
                    observeTransient(mvm.checkingForUpdates) {
                        binding.btnUpdate.isEnabled = !it
                    }
                    observeTransient(mvm.checkingForUpdatesError) {
                        toast(R.string.format_request_failed.format(it))
                    }
                    observed = true
                }
                mvm.checkForUpdates()
            } else {
                if (app.updateInfo.value?.hasUpdates() == true) {
                    mvm.showUpdateDialog = true
                    app.updateInfo.notifySelfChanged()
                } else {
                    toast(R.string.no_updates)
                }
            }
        }
        doOnEventRouted(HOST_PRIVACY_POLICY) {
            PrivacyPolicyDialog().show(childFragmentManager)
        }
    }
}