package com.vk.limextask.model.channel.mapper

import com.vk.limextask.domain.channel.IChannelItem
import com.vk.limextask.domain.channel.ICurrentVideo
import com.vk.limextask.model.channel.vo.ChannelItemVO
import com.vk.limextask.model.channel.vo.CurrentVideoVO

object ChannelMapper {
    fun transform(item : IChannelItem): ChannelItemVO {
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
            cdn = item.cdn
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