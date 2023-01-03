package com.vk.limextask.model.channel.mapper

import com.vk.limextask.data.entity.ItemFavoriteDbModel
import com.vk.limextask.model.channel.ChannelId

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