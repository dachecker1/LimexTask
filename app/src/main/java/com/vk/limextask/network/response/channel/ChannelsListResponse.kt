package com.vk.limextask.network.response.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ChannelsListResponse(
    @SerializedName("channels") @Expose val channels : List<ChannelResponse> = listOf()
)