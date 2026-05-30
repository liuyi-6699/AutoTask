/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.editor

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import top.xjunz.tasker1.R
import top.xjunz.tasker1.databinding.DialogTaskMetadataEditorBinding
import top.xjunz.tasker1.engine.task.XTask.Metadata
import top.xjunz.tasker1.ktx.*
import top.xjunz.tasker1.ui.base.BaseDialogFragment
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener

/**
 * @author Mengran 2022/12/15
 */
class TaskMetadataEditor : BaseDialogFragment<DialogTaskMetadataEditorBinding>() {

    override val isFullScreen: Boolean = false

    private class InnerViewModel : ViewModel() {

        lateinit var onCompletion: () -> Unit

        lateinit var metadata: Metadata
    }

    private val viewModel by viewModels<InnerViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showSoftInput(binding.etTaskName)
        binding.btnCancel.setNoDoubleClickListener {
            dismiss()
        }
        binding.etTaskName.setText(viewModel.metadata.title)
        binding.etTaskDesc.setText(viewModel.metadata.description)
        if (viewModel.metadata.title == R.string.unnamed_task.str) {
            binding.etTaskName.selectAll()
        } else {
            binding.etTaskName.setSelectionToEnd()
        }
        binding.btnComplete.setNoDoubleClickListener {
            if (binding.etTaskName.textString.isEmpty()) {
                toast(R.string.error_empty_input)
                binding.etTaskName.shake()
                return@setNoDoubleClickListener
            }
            viewModel.metadata.title = binding.etTaskName.textString
            viewModel.metadata.description = binding.etTaskDesc.textString
            viewModel.onCompletion()
            dismiss()
        }
    }

    fun init(initialMetadata: Metadata, doOnCompletion: () -> Unit) =
        doWhenCreated {
            viewModel.metadata = initialMetadata
            viewModel.onCompletion = doOnCompletion
        }
}