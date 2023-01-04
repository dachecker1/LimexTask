package com.vk.limextask.data.channel.vo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CurrentVideoVO(
    var timeStart : Long = 0,
    var timeStop : Long = 0,
    var title : String = "",
    var desc : String = "",
    var cdnVideo : Int = 0,
    var rating : Int = 0
) : Parcelable