package com.vk.limextask.network

import com.vk.limextask.network.response.channel.ChannelsListResponse
import retrofit2.http.GET

interface ILimexRestApi {

    @GET("playlist/channels.json")
    suspend fun getChannels() : ChannelsListResponse
}