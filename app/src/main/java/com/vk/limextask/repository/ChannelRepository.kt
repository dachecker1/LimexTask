package com.vk.limextask.repository

import com.vk.limextask.network.ILimexRestApi
import com.vk.limextask.network.response.channel.ChannelResponse

class ChannelRepository(private val mLimexRestApi : ILimexRestApi) {
    suspend fun getChannelList() : List<ChannelResponse> =
        mLimexRestApi.getChannels().channels
}