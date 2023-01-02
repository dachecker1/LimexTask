package com.vk.limextask.di

import com.vk.limextask.view_models.ChannelListViewModel
import com.vk.limextask.view_models.FavoriteChannelsListViewModel
import com.vk.limextask.view_models.PlayerViewModel
import com.vk.limextask.view_models.AllChannelListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { ChannelListViewModel() }
    viewModel { AllChannelListViewModel(get()) }
    viewModel { PlayerViewModel() }
    viewModel { FavoriteChannelsListViewModel(get()) }
}