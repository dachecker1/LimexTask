package com.vk.limextask.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.limextask.R
import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.data.channel.vo.ChannelItemVO
import com.vk.limextask.databinding.RvChannelItemBinding
import com.vk.limextask.presentation.adapter.diffutil.ChannelItemDiffCallback
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ChannelsListAdapter :
    ListAdapter<ChannelItemVO, ChannelsListAdapter.ViewHolder>(ChannelItemDiffCallback()) {

    val didChannelClickFlow = MutableSharedFlow<ChannelItemVO>()
    val didFavoriteClickFlow = MutableSharedFlow<Int>()

    var favoriteList = listOf<ChannelId>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_channel_item, parent, false)
        return ViewHolder(RvChannelItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val channelItem = getItem(position)

        holder.itemView.setOnClickListener {
            ViewTreeLifecycleOwner.get(it)?.lifecycleScope?.launch{
                didChannelClickFlow.emit(channelItem)
            }
        }

        holder.favorite.setOnClickListener {
            ViewTreeLifecycleOwner.get(it)?.lifecycleScope?.launch {
                didFavoriteClickFlow.emit(channelItem.id)
            }
        }

        Picasso.get()
            .load(channelItem.image.ifEmpty { null })
            .placeholder(R.drawable.limex_logo)
            .fit()
            .centerInside()
            .into(holder.image)
        holder.title.text = channelItem.nameRus
        holder.desc.text = channelItem.current.title
        holder.favorite.isChecked = channelItem.isFavorite
    }

        class ViewHolder(private val binding: RvChannelItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val image = binding.channelLogo
            val title = binding.tvChannelName
            val desc = binding.tvChannelDesc
            val favorite = binding.imFavorite
        }
}