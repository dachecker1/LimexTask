package com.vk.limextask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.vk.limextask.R
import com.vk.limextask.data.entity.ItemFavoriteDbModel
import com.vk.limextask.model.channel.vo.ChannelItemVO
import com.vk.limextask.presentation.adapter.diffutil.ChannelItemDiffCallback
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class ChannelsListAdapter :
    ListAdapter<ChannelItemVO, ChannelsListAdapter.ViewHolder>(ChannelItemDiffCallback()) {

    val didChannelClickFlow = MutableSharedFlow<ChannelItemVO>()
    val didFavoriteClickFlow = MutableSharedFlow<Int>()

    var favoriteList = listOf<ItemFavoriteDbModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_channel_item, parent, false)
        return ViewHolder(view)
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
//                if(!holder.favorite.isChecked)  holder.favorite.isChecked = true
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
        val isFavorite = favoriteList.find { it.itemId == channelItem.id }
        holder.favorite.isChecked = isFavorite != null
    }


        class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
            val image = view.findViewById<ImageView>(R.id.channel_logo)
            val title = view.findViewById<TextView>(R.id.tv_channel_name)
            val desc = view.findViewById<TextView>(R.id.tv_channel_desc)
            val favorite = view.findViewById<RadioButton>(R.id.im_favorite)
        }
}