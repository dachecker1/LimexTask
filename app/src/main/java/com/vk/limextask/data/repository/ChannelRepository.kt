package com.vk.limextask.data.repository

import android.app.Application
import com.vk.limextask.data.FavoriteChannelDataBase
import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.data.channel.mapper.FavoriteChannelMapper
import com.vk.limextask.network.ILimexRestApi
import com.vk.limextask.network.response.channel.ChannelResponse

class ChannelRepository(
    private val mLimexRestApi : ILimexRestApi,
    private val application: Application
    ) {

    private val favoriteChannelsDao by lazy {
        FavoriteChannelDataBase.getInstance(application = application).favoriteChannelsListDao()
    }

    suspend fun getChannelList() : List<ChannelResponse> =
        mLimexRestApi.getChannels().channels

    fun getFavoriteChannelList() : List<ChannelId> =
        favoriteChannelsDao.getFavoriteChannelsListLiveData().map {
            FavoriteChannelMapper.transform(it)
    }

    suspend fun removeChannelFromFavoriteList(channelId : Int) {
        favoriteChannelsDao.deleteChannel(channelId)
    }

    suspend fun addChannelToFavoriteList(channelId : Int) {
        favoriteChannelsDao.addChannel(FavoriteChannelMapper.transform(channelId))
    }
}