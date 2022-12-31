package com.vk.limextask.domain.channel

interface ICurrentVideo {
    val timeStart : Long
    val timeStop : Long
    val title : String
    val desc : String
    val cdnVideo : Int
    val rating : Int
}