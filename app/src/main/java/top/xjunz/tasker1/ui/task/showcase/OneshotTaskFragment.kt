/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.showcase

import android.os.Bundle
import android.view.View
import top.xjunz.tasker1.engine.task.XTask
import top.xjunz.tasker1.ktx.observeTransient
import top.xjunz.tasker1.task.storage.TaskStorage
import top.xjunz.tasker1.ui.main.EventCenter

/**
 * @author Mengran 2023/02/22
 */
class OneshotTaskFragment : BaseTaskShowcaseFragment() {

    companion object {
        const val EVENT_ONESHOT_TASK_ADDED = "xjunz.event.ONESHOT_TASK_ADDED"
    }

    override fun initTaskList(): List<XTask> {
        return TaskStorage.getAllTasks().filter { it.isOneshot }
    }

    override val index: Int = 2

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeTransient(viewModel.onNewTaskAdded) {
            if (taskList.contains(it)) return@observeTransient
            if (it.metadata.taskType != XTask.TYPE_ONESHOT) return@observeTransient
            taskList.add(it)
            if (taskList.size == 1) togglePlaceholder(false)
            adapter.notifyItemInserted(taskList.lastIndex)
            EventCenter.sendEvent(EVENT_ONESHOT_TASK_ADDED, taskList.lastIndex)
            notifyBadgeNumberChanged()
        }
    }
}