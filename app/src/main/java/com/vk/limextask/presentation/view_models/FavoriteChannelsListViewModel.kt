package com.vk.limextask.presentation.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.limextask.domain.interactor.ChannelInteractor
import com.vk.limextask.data.channel.ChannelId
import com.vk.limextask.data.channel.vo.ChannelItemVO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteChannelsListViewModel(
    private val channelInteractor: ChannelInteractor,
    private val dispatcherIO : CoroutineDispatcher
) : ViewModel() {

    private val _channelList = MutableLiveData<List<ChannelItemVO>>()
    val channelList: LiveData<List<ChannelItemVO>>
        get() = _channelList

    private val _favoriteChannelListDB = MutableLiveData<List<ChannelId>>()

    init {
        getFavoriteChannelList()
    }

    fun onFavoriteClick(channelId: Int) {
        changeFavoriteStatus(channelId)
        getFavoriteChannelList()
    }

    fun getFavoriteChannelList() {
        val channelListDb = viewModelScope.launch(dispatcherIO) {
            _favoriteChannelListDB.postValue(channelInteractor.getFavoriteChannelList())
        }

        viewModelScope.launch(dispatcherIO) {
            channelListDb.join()
            channelInteractor.getChannelList()
                .catch { it.printStackTrace() }
                .collect { channelItemList ->
                    val list = arrayListOf<ChannelItemVO>()
                    _favoriteChannelListDB.value?.forEach { favoriteChannelDB ->
                        val channel = channelItemList.find { it.id == favoriteChannelDB.itemId }
                        if (channel != null) list.add(channel)
                    }
                    _channelList.postValue(list)
                }
        }
    }

    private fun changeFavoriteStatus(channelId: Int) {
        val result = viewModelScope.launch(dispatcherIO) {
            channelInteractor.changeFavoriteStatus(channelId)
        }
        viewModelScope.launch(dispatcherIO) {
            result.join()
            _favoriteChannelListDB.postValue(channelInteractor.getFavoriteChannelList())
        }
    }
}