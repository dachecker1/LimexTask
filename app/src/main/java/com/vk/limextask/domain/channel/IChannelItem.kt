package com.vk.limextask.domain.channel

import com.vk.limextask.network.response.channel.CurrentVideo

interface IChannelItem {
    val id : Int
    val epgId : Int
    val nameRus : String
    val nameEng : String
    val vitrinaEventsUrl : String
    val isFederal : Boolean
    val address : String
    val image : String
    val hasEpg : Boolean
    val current : CurrentVideo
    val foreignPLayerKey : Boolean
    val url : String
    val urlSound : String
    val cdn : String
}