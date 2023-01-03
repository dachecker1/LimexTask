package com.vk.limextask.presentation

import androidx.lifecycle.lifecycleScope
import com.vk.limextask.view_models.FavoriteChannelsListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteChannelsListFragment : AllChannelsListFragment() {

    private val viewModel : FavoriteChannelsListViewModel by viewModel()

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteChannelList()
    }

    override fun subscribeLiveData() = with(viewModel) {
        channelList.observe(viewLifecycleOwner) {
            channelsListAdapter.submitList(it)
        }
        favoriteChannelListDB.observe(viewLifecycleOwner) {
            channelsListAdapter.favoriteList = it
        }
    }

    override fun collectClicks() {
        super.collectClicks()
        viewLifecycleOwner.lifecycleScope.launch {
            { }
            channelsListAdapter.didFavoriteClickFlow.collectLatest {
                viewModel.changeFavoriteStatus(it)
                viewModel.getFavoriteChannelList()
            }
        }
    }
}