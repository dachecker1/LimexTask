package com.vk.limextask.network.response.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.limextask.domain.channel.ICurrentVideo

class CurrentVideo : ICurrentVideo {
    @SerializedName("timestart") @Expose override val timeStart: Long = 0
    @SerializedName("timestop") @Expose override val timeStop: Long = 0
    @SerializedName("title") @Expose override val title: String = ""
    @SerializedName("desc") @Expose override val desc: String = ""
    @SerializedName("cdnvideo") @Expose override val cdnVideo: Int = 0
    @SerializedName("rating") @Expose override val rating: Int = 0
}