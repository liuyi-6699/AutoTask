/*
 * Copyright (c) 2024 Mengran. All rights reserved.
 */

package top.xjunz.tasker1.ui.task.selector.argument

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import top.xjunz.tasker1.databinding.ItemActivityInfoBinding
import top.xjunz.tasker1.ktx.require
import top.xjunz.tasker1.ui.model.ActivityInfoWrapper
import top.xjunz.tasker1.util.ClickListenerUtil.setNoDoubleClickListener

/**
 * @author Mengran 2022/10/09
 */
class ActivityInfoAdapter(
    private val viewModel: ComponentSelectorViewModel,
    private val activitiesInfo: List<ActivityInfoWrapper>,
    private val host: ComponentSelectorDialog
) : RecyclerView.Adapter<ActivityInfoAdapter.ActivityInfoViewHolder>() {

    inner class ActivityInfoViewHolder(val binding: ItemActivityInfoBinding) :
        ViewHolder(binding.root) {
        init {
            binding.root.setNoDoubleClickListener {
                host.onActivityItemClicked(activitiesInfo[adapterPosition], binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityInfoViewHolder {
        return ActivityInfoViewHolder(
            ItemActivityInfoBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ActivityInfoViewHolder, position: Int) {
        val pkgInfo = viewModel.selectedPackage.require()
        val actInfo = activitiesInfo[position]
        holder.binding.let {
            viewModel.iconLoader.loadIconTo(pkgInfo, it.ivIcon, host)
            it.tvActivityName.text = actInfo.label
            it.tvFullName.text = actInfo.source.name
            it.tvBadge.isVisible = actInfo.isEntrance
            it.root.isSelected = viewModel.selectedActivities.contains(actInfo.componentName)
        }
    }

    override fun getItemCount(): Int {
        return activitiesInfo.size
    }
}