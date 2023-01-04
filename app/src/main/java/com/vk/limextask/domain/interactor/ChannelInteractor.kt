package com.vk.limextask.domain.interactor

import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.data.channel.mapper.ChannelMapper
import com.vk.limextask.data.channel.vo.ChannelItemVO
import com.vk.limextask.data.repository.ChannelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class ChannelInteractor(
    private val channelRepository: ChannelRepository
    ) {

    suspend fun getChannelList(): Flow<List<ChannelItemVO>> = flow {
        emit(channelRepository.getChannelList()
            .map { ChannelMapper.transform(it,getFavoriteChannelList()) })
    }

    suspend fun getFavoriteChannelList() : List<ChannelId> {
        return channelRepository.getFavoriteChannelList()
    }

    suspend fun changeFavoriteStatus(channelId : Int) {
        val favoriteChannelList = getFavoriteChannelList()
        val itemFavoriteDbModel = favoriteChannelList.find { it.itemId == channelId }
        if(itemFavoriteDbModel != null) {
            channelRepository.removeChannelFromFavoriteList(channelId)
        } else {
            channelRepository.addChannelToFavoriteList(channelId)
        }
    }

    suspend fun getFavoriteChannelListFromDB() : List<ChannelItemVO> {
        var favoriteList = listOf<ChannelId>()
        val channelList = arrayListOf<ChannelItemVO>()
        val channelListDB = CoroutineScope(Dispatchers.IO).launch {
            favoriteList = getFavoriteChannelList()
        }
        CoroutineScope(Dispatchers.IO).launch {
            channelListDB.join()
            getChannelList()
                .catch { it.printStackTrace() }
                .collect{ channelItemList ->
                    favoriteList.forEach { favoriteChannelDB ->
                        val channel = channelItemList.find { it.id == favoriteChannelDB.itemId }
                        if (channel != null) channelList.add(channel)
                    }
                }
        }
        return channelList
    }
}