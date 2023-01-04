package com.vk.limextask.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.limextask.domain.interactor.ChannelInteractor
import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.data.channel.vo.ChannelItemVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllChannelListViewModel(private val channelInteractor: ChannelInteractor) : ViewModel() {

    private val _channelList = MutableLiveData<List<ChannelItemVO>>()
    val channelList: LiveData<List<ChannelItemVO>>
        get() = _channelList

    private val _favoriteChannelList = MutableLiveData<List<ChannelId>>()
    val favoriteChannelList: LiveData<List<ChannelId>>
        get() = _favoriteChannelList

    init {
        getChannelList()
    }

    fun getChannelList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _favoriteChannelList.postValue(channelInteractor.getFavoriteChannelList())

                channelInteractor.getChannelList()
                    .catch { it.printStackTrace() }
                    .collect {
                        _channelList.postValue(it)
                    }
            }
//            _favoriteChannelList.postValue(channelInteractor.getFavoriteChannelList())
//
//            channelInteractor.getChannelList()
//                .catch { it.printStackTrace() }
//                .collect {
//                    _channelList.postValue(it)
//                }
        }
    }

    fun changeFavoriteStatus(channelId: Int) {
        val result = viewModelScope.launch(Dispatchers.IO) {
            channelInteractor.changeFavoriteStatus(channelId)
        }
        viewModelScope.launch(Dispatchers.IO) {
            result.join()
            _favoriteChannelList.postValue(channelInteractor.getFavoriteChannelList())
            getChannelList()
        }
    }
}