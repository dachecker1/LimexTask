package com.vk.limextask.network.response.channel

import com.google.gson.annotations.SerializedName
import com.vk.limextask.domain.channel.IChannelItem

class ChannelResponse (
    @SerializedName("id") override val id: Int,
    @SerializedName("epg_id") override val epgId: Int,
    @SerializedName("name_ru") override val nameRus: String,
    @SerializedName("name_en") override val nameEng: String,
    @SerializedName("vitrina_events_url") override val vitrinaEventsUrl: String,
    @SerializedName("is_federal") override val isFederal: Boolean,
    @SerializedName("address") override val address: String,
    @SerializedName("image") override val image: String,
    @SerializedName("hasEpg") override val hasEpg: Boolean,
    @SerializedName("current") override val current: CurrentVideo,
    @SerializedName("foreign_player_key") override val foreignPLayerKey: Boolean,
    @SerializedName("url") override val url: String,
    @SerializedName("url_sound") override val urlSound: String,
    @SerializedName("cdn") override val cdn: String
) : IChannelItem