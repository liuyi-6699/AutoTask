/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.editor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import top.xjunz.shared.ktx.casted
import top.xjunz.tasker1.R
import top.xjunz.tasker1.databinding.DialogSnapshotSelectorBinding
import top.xjunz.tasker1.databinding.ItemTaskSnapshotBinding
import top.xjunz.tasker1.ktx.*
import top.xjunz.tasker1.ui.base.BaseBottomSheetDialog
import top.xjunz.tasker1.ui.base.inlineAdapter
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener
import top.xjunz.tasker1.util.formatMinSecMills
import top.xjunz.tasker1.util.formatTime

/**
 * @author Mengran 2023/01/26
 */

@SuppressLint("SetTextI18n")
class TaskSnapshotSelectorDialog : BaseBottomSheetDialog<DialogSnapshotSelectorBinding>() {

    private val gvm by lazy {
        requireParentFragment().casted<FlowEditorDialog>().gvm
    }

    private val adapter by lazy {
        inlineAdapter(gvm.allSnapshots.require(), ItemTaskSnapshotBinding::class.java, {
            binding.root.setNoDoubleClickListener {
                gvm.currentSnapshotIndex.value = adapterPosition
                dismiss()
            }
        }) { binding, index, snapshot ->
            binding.root.isSelected = gvm.currentSnapshotIndex eq index
            binding.tvInfo.text = if (snapshot.isSuccessful && snapshot.duration != 0) {
                R.string.format_task_snapshot_info_2.formatAsHtml(
                    snapshot.startTimestamp.formatTime(), formatMinSecMills(snapshot.duration)
                )
            } else {
                snapshot.startTimestamp.formatTime()
            }

            if (snapshot.isRunning) {
                binding.ivResult.contentDescription = R.string.running.str
                binding.ivResult.setImageResource(R.drawable.ic_help_24px)
            } else if (snapshot.isSuccessful) {
                binding.ivResult.contentDescription = R.string.succeeded.str
                binding.ivResult.setImageResource(R.drawable.ic_check_circle_24px)
            } else {
                binding.ivResult.contentDescription = R.string.failed.str
                binding.ivResult.setImageResource(R.drawable.ic_cancel_24px)
            }
            binding.tvNumber.text = (index + 1).toString()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(gvm.allSnapshots) {
            binding.rvSnapshot.adapter = adapter
        }
        observeNotNull(gvm.currentSnapshotIndex) {
            binding.rvSnapshot.scrollPositionToCenterVertically(it, true)
        }
    }
}