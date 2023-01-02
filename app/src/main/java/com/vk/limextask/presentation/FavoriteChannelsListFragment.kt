package com.vk.limextask.presentation

import androidx.fragment.app.Fragment
import com.vk.limextask.view_models.FavoriteChannelsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteChannelsListFragment : Fragment() {

    private val viewModel : FavoriteChannelsListViewModel by viewModel()


}