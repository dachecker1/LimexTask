package com.vk.limextask.data.channel.mapper

import com.vk.limextask.data.entity.ItemFavoriteDbModel
import com.vk.limextask.data.channel.ChannelId

object FavoriteChannelMapper {
    fun transform(channelId : Int) : ItemFavoriteDbModel {
        return ItemFavoriteDbModel(
            itemId = channelId
        )
    }

    fun transform(item : ItemFavoriteDbModel) : ChannelId {
        return ChannelId(
            itemId = item.itemId
        )
    }
}