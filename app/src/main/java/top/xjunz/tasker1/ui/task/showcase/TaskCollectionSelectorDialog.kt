/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.showcase

import android.os.Bundle
import android.view.View
import top.xjunz.tasker1.databinding.DialogTaskCollectionSelectorBinding
import top.xjunz.tasker1.ktx.show
import top.xjunz.tasker1.ui.base.BaseBottomSheetDialog
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener

/**
 * @author Mengran 2023/09/17
 */
class TaskCollectionSelectorDialog : BaseBottomSheetDialog<DialogTaskCollectionSelectorBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.containerExampleTasks.setNoDoubleClickListener {
            TaskListDialog().setExampleTaskMode().show(requireActivity().supportFragmentManager)
            dismiss()
        }
        binding.containerPreloadTasks.setNoDoubleClickListener {
            TaskListDialog().setPreloadTaskMode().show(requireActivity().supportFragmentManager)
            dismiss()
        }
    }
}