package com.vk.limextask.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vk.limextask.model.channel.vo.ChannelItemVO

class PlayerViewModel : ViewModel() {

    private val _channelItemVO = MutableLiveData<ChannelItemVO>()
    val channelItemVO: LiveData<ChannelItemVO>
        get() = _channelItemVO

    fun getUrl() = _channelItemVO.value!!.url

    fun setChannelItem(item: ChannelItemVO) {
        _channelItemVO.value = item
    }
}