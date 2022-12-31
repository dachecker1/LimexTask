package com.vk.limextask.model.channel.vo

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ChannelItemVO(
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
    var cdn : String = ""
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + epgId
        result = 31 * result + nameRus.hashCode()
        result = 31 * result + nameEng.hashCode()
        result = 31 * result + vitrinaEventsUrl.hashCode()
        result = 31 * result + isFederal.hashCode()
        result = 31 * result + address.hashCode()
        result = 31 * result + image.hashCode()
        result = 31 * result + hasEpg.hashCode()
        result = 31 * result + current.hashCode()
        result = 31 * result + foreignPlayerKey.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + urlSound.hashCode()
        result = 31 * result + cdn.hashCode()
        return result
    }
}
