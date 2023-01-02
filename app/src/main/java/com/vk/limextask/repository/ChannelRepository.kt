package com.vk.limextask.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.vk.limextask.data.FavoriteChannelDataBase
import com.vk.limextask.data.entity.ItemFavoriteDbModel
import com.vk.limextask.model.channel.mapper.FavoriteChannelMapper
import com.vk.limextask.network.ILimexRestApi
import com.vk.limextask.network.response.channel.ChannelResponse

class ChannelRepository(
    private val mLimexRestApi : ILimexRestApi,
    private val application: Application
    ) {

    private val favoriteChannelsDao by lazy {
        FavoriteChannelDataBase.getInstance(application = application).favotiteChannelsListDao()
    }

    suspend fun getChannelList() : List<ChannelResponse> =
        mLimexRestApi.getChannels().channels

    fun getFavoriteChannelList() : List<ItemFavoriteDbModel> {
        return favoriteChannelsDao.getFavoriteChannelsListLiveData()
    }

    suspend fun removeChannelFromFavoriteList(channelId : Int) {
        favoriteChannelsDao.deleteChannel(channelId)
    }

    suspend fun addChannelToFavoriteList(channelId : Int) {
        favoriteChannelsDao.addChannel(FavoriteChannelMapper.transform(channelId))
    }
}