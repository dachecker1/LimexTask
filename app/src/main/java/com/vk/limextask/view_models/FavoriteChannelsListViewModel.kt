package com.vk.limextask.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vk.limextask.data.entity.ItemFavoriteDbModel
import com.vk.limextask.domain.interactor.ChannelInteractor
import com.vk.limextask.model.channel.vo.ChannelItemVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteChannelsListViewModel(
    private val channelInteractor: ChannelInteractor
) : ViewModel() {

    private val _channelList = MutableLiveData<List<ChannelItemVO>>()
    val channelList: LiveData<List<ChannelItemVO>>
        get() = _channelList

    private val _favoriteChannelListDB = MutableLiveData<List<ItemFavoriteDbModel>>()

    init {
        getFavoriteChannelList()
    }

    fun getFavoriteChannelList() {
        viewModelScope.launch(Dispatchers.IO) {
            _favoriteChannelListDB.postValue(channelInteractor.getFavoriteChannelList())

            channelInteractor.getChannelList()
                .catch { it.printStackTrace() }
                .collect { channelItemList ->
                    val list = arrayListOf<ChannelItemVO>()
                    _favoriteChannelListDB.value?.forEachIndexed { index, favoriteChannelDB ->
                        val channel = channelItemList.find { it.id == favoriteChannelDB.itemId }
                        if (channel != null) list.add(channel)
                    }
                    _channelList.postValue(list)
                }
        }
    }

    fun changeFavoriteStatus(channelId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            channelInteractor.changeFavoriteStatus(channelId)
            _favoriteChannelListDB.postValue(channelInteractor.getFavoriteChannelList())
        }
    }
}