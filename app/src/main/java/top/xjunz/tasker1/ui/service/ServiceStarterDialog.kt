/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.service

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import top.xjunz.tasker1.BuildConfig
import top.xjunz.tasker1.R
import top.xjunz.tasker1.app
import top.xjunz.tasker1.databinding.DialogServiceStarterBinding
import top.xjunz.tasker1.ktx.beginAutoTransition
import top.xjunz.tasker1.ktx.observe
import top.xjunz.tasker1.ktx.text
import top.xjunz.tasker1.ktx.toast
import top.xjunz.tasker1.service.OperatingMode
import top.xjunz.tasker1.service.serviceController
import top.xjunz.tasker1.ui.base.BaseBottomSheetDialog
import top.xjunz.tasker1.ui.main.MainViewModel.Companion.peekMainViewModel
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener

/**
 * @author Mengran 2022/12/24
 */
class ServiceStarterDialog : BaseBottomSheetDialog<DialogServiceStarterBinding>() {

    private val mvm by lazy {
        peekMainViewModel()
    }

    private lateinit var overlaySettingLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overlaySettingLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (!Settings.canDrawOverlays(app)) {
                    toast(R.string.grant_failed)
                }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnStart.setNoDoubleClickListener {
            serviceController.bindService()
        }
        binding.rgModes.check(
            if (OperatingMode.CURRENT == OperatingMode.Privilege)
                R.id.rb_mode_shizuku else R.id.rb_mode_a11y
        )
        binding.rgModes.setOnCheckedChangeListener { _, checkedId ->
            mvm.setCurrentOperatingMode(
                if (checkedId == R.id.rb_mode_shizuku) OperatingMode.Privilege
                else OperatingMode.Accessibility
            )
        }
        binding.btnGrantOverlay.setNoDoubleClickListener {
            toast(R.string.pls_enable_overlay_manually)
            overlaySettingLauncher.launch(
                Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                    .setData(Uri.parse("package:${BuildConfig.APPLICATION_ID}"))
            )
        }
        observe(mvm.operatingMode) {
            binding.root.rootView.beginAutoTransition()
            binding.containerOverlayPermission.isVisible = it == OperatingMode.Accessibility
            if (it == OperatingMode.Privilege) {
                binding.tvMode.text = R.string.shizuku.text
                binding.tvModeIntro.text = R.string.desc_shizuku_mode.text
            } else {
                binding.tvMode.text = R.string.a11y_service.text
                binding.tvModeIntro.text = R.string.desc_a11y_mode.text
            }
        }

        observe(mvm.isServiceBinding) {
            binding.btnStart.isEnabled = !it
            if (it) {
                binding.spreadContainer.clearSpreading()
                binding.tvState.text = R.string.launching.text
            } else {
                binding.spreadContainer.startSpreading()
                binding.tvState.text = R.string.service_not_started.text
            }
        }
        observe(mvm.isServiceRunning) {
            if (it) {
                toast(R.string.service_started)
                dismiss()
            }
        }
    }

    private fun updateOverlayButton() {
        binding.btnGrantOverlay.let {
            it.isEnabled = !Settings.canDrawOverlays(app)
            if (it.isEnabled) {
                it.setIconResource(R.drawable.ic_chevron_right_24px)
                it.text = R.string.goto_grant.text
            } else {
                it.setIconResource(R.drawable.ic_done_24px)
                it.text = R.string.granted.text
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateOverlayButton()
    }

}