package com.vk.limextask.data.channel.mapper

import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.domain.channel.IChannelItem
import com.vk.limextask.domain.channel.ICurrentVideo
import com.vk.limextask.data.channel.vo.ChannelItemVO
import com.vk.limextask.data.channel.vo.CurrentVideoVO

object ChannelMapper {
    fun transform(item : IChannelItem, list : List<ChannelId>): ChannelItemVO {
        val isFavorite = list.find { it.itemId == item.id }
        return ChannelItemVO(
            id = item.id,
            epgId = item.epgId,
            nameRus = item.nameRus,
            nameEng = item.nameEng,
            vitrinaEventsUrl = item.vitrinaEventsUrl,
            isFederal = item.isFederal,
            address = item.address,
            image = item.image,
            hasEpg = item.hasEpg,
            current = transform(item.current),
            foreignPlayerKey = item.foreignPLayerKey,
            url = item.url,
            urlSound = item.urlSound,
            cdn = item.cdn,
            isFavorite = isFavorite != null
        )
    }

    private fun transform(item : ICurrentVideo) : CurrentVideoVO {
        return CurrentVideoVO(
            timeStart = item.timeStart,
            timeStop = item.timeStop,
            title = item.title,
            desc = item.desc,
            cdnVideo = item.cdnVideo,
            rating = item.rating
        )
    }
}