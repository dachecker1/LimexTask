package com.vk.limextask.data.channel.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChannelItemVO(
    var id : Int = 0,
    var epgId : Int = 0,
    var nameRus : String = "",
    var nameEng : String = "",
    var vitrinaEventsUrl : String = "",
    var isFederal : Boolean = false,
    var address : String = "",
    var image : String = "",
    var hasEpg : Boolean = false,
    var current : CurrentVideoVO = CurrentVideoVO(),
    var foreignPlayerKey : Boolean = false,
    var url : String = "",
    var urlSound : String = "",
    var cdn : String = "",
    var isFavorite : Boolean = false
) : Parcelable
