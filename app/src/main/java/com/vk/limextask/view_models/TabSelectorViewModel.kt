package com.vk.limextask.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.limextask.domain.interactor.ChannelInteractor
import com.vk.limextask.model.channel.vo.ChannelItemVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TabSelectorViewModel(private val channelInteractor: ChannelInteractor) : ViewModel() {

    private val _channelList = MutableLiveData<List<ChannelItemVO>>()
    val channelList: LiveData<List<ChannelItemVO>>
        get() = _channelList

    init {
        viewModelScope.launch(Dispatchers.IO) {
            channelInteractor.getChannelList()
                .catch { it.printStackTrace() }
                .collect {
                    _channelList.postValue(it)
                }
        }
    }

    fun changeFavoriteStatus(channelId : Int) {

    }

}