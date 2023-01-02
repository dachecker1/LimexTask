package com.vk.limextask.di

import com.vk.limextask.view_models.ChannelListViewModel
import com.vk.limextask.view_models.PlayerViewModel
import com.vk.limextask.view_models.TabSelectorViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val appModule = module {
    viewModel { ChannelListViewModel() }
    viewModel { TabSelectorViewModel(get()) }
    viewModel { PlayerViewModel() }
}