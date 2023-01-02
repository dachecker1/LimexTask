package com.vk.limextask.model.channel.mapper

import com.vk.limextask.data.entity.ItemFavoriteDbModel

object FavoriteChannelMapper {
    fun transform(channelId : Int) : ItemFavoriteDbModel {
        return ItemFavoriteDbModel(
            itemId = channelId
        )
    }
}