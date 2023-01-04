package com.vk.limextask.network.response.channel

import com.google.gson.annotations.SerializedName

class ChannelsListResponse(
    @SerializedName("channels") val channels : List<ChannelResponse> = listOf()
)