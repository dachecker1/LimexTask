package com.vk.limextask.presentation.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.vk.limextask.data.channel.vo.ChannelItemVO

class ChannelItemDiffCallback : DiffUtil.ItemCallback<ChannelItemVO>() {
    override fun areItemsTheSame(oldItem: ChannelItemVO, newItem: ChannelItemVO): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ChannelItemVO, newItem: ChannelItemVO): Boolean {
        return oldItem == newItem
    }
}