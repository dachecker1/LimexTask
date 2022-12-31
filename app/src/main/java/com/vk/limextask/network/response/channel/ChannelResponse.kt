package com.vk.limextask.network.response.channel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.vk.limextask.domain.channel.IChannelItem

class ChannelResponse (
    @SerializedName("id") @Expose override val id: Int,
    @SerializedName("epg_id") @Expose override val epgId: Int,
    @SerializedName("name_ru") @Expose override val nameRus: String,
    @SerializedName("name_en") @Expose override val nameEng: String,
    @SerializedName("vitrina_events_url") @Expose override val vitrinaEventsUrl: String,
    @SerializedName("is_federal") @Expose override val isFederal: Boolean,
    @SerializedName("address") @Expose override val address: String,
    @SerializedName("image") @Expose override val image: String,
    @SerializedName("hasEpg") @Expose override val hasEpg: Boolean,
    @SerializedName("current") @Expose override val current: CurrentVideo,
    @SerializedName("foreign_player_key") @Expose override val foreignPLayerKey: Boolean,
    @SerializedName("url") @Expose override val url: String,
    @SerializedName("url_sound") @Expose override val urlSound: String,
    @SerializedName("cdn") @Expose override val cdn: String
) : IChannelItem