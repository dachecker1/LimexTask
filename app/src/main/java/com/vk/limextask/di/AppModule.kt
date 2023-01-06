package com.vk.limextask.di

import com.vk.limextask.presentation.view_models.FavoriteChannelsListViewModel
import com.vk.limextask.presentation.view_models.PlayerViewModel
import com.vk.limextask.presentation.view_models.AllChannelListViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named

val appModule = module {
    viewModel { AllChannelListViewModel(get(), get(named("IODispatcher"))) }
    viewModel { PlayerViewModel() }
    viewModel { FavoriteChannelsListViewModel(get(), get(named("IODispatcher"))) }
}