package com.vk.limextask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.limextask.data.channel.vo.ChannelItemVO
import com.vk.limextask.databinding.FragmentChannelListBinding
import com.vk.limextask.presentation.adapter.ChannelsListAdapter
import com.vk.limextask.presentation.view_models.FavoriteChannelsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteChannelsListFragment : Fragment() {
    private var _binding: FragmentChannelListBinding? = null
    private val binding: FragmentChannelListBinding
        get() = _binding ?: throw RuntimeException("FragmentChannelListBinding is null")

    private val viewModel : FavoriteChannelsListViewModel by viewModel()
    lateinit var channelsListAdapter : ChannelsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChannelListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeLiveData()
        collectClicks()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteChannelList()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun setupRecyclerView(){
        val rv = binding.tabSelectorRv
        channelsListAdapter = ChannelsListAdapter()
        rv.layoutManager = LinearLayoutManager(requireContext())
        rv.adapter = channelsListAdapter
    }

    private fun subscribeLiveData() = with(viewModel) {
        channelList.observe(viewLifecycleOwner) {
            channelsListAdapter.submitList(it)
        }
    }

     private fun collectClicks() {
         channelsListAdapter.didChannelClickListener = ::openFullScreenVideo

         channelsListAdapter.didFavoriteClickListener = viewModel::onFavoriteClick
     }

    private fun openFullScreenVideo(item: ChannelItemVO) {
        findNavController().navigate(ContentFragmentDirections.navigateToPlayer(item))
    }
}