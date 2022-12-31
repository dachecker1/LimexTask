package com.vk.limextask.domain.interactor

import com.vk.limextask.model.channel.mapper.ChannelMapper
import com.vk.limextask.model.channel.vo.ChannelItemVO
import com.vk.limextask.repository.ChannelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChannelInteractor(private val channelRepository: ChannelRepository) {

    suspend fun getChannelList(): Flow<List<ChannelItemVO>> = flow {
        emit(channelRepository.getChannelList()
            .map { ChannelMapper.transform(it) })
    }
}