package com.vk.limextask.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.vk.limextask.databinding.FragmentTabSelectorBinding
import com.vk.limextask.presentation.adapter.ChannelsListAdapter
import com.vk.limextask.view_models.TabSelectorViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

const val ARG_OBJECT = "object"

class TabSelectorFragment : Fragment() {
    private var _binding: FragmentTabSelectorBinding? = null
    private val binding: FragmentTabSelectorBinding
        get() = _binding ?: throw RuntimeException("FragmentTabSelectorBinding is null")

    private val viewModel : TabSelectorViewModel by viewModel()
    private lateinit var channelsListAdapter : ChannelsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTabSelectorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeLiveData()
        setupViews()
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

    private fun subscribeLiveData(){
        viewModel.channelList.observe(viewLifecycleOwner) {
            channelsListAdapter.submitList(it)
        }
    }

    private fun setupViews(){
        viewLifecycleOwner.lifecycleScope.launch {
            {  }
            channelsListAdapter.didClickFlow.collectLatest {
                viewModel.openFullScreenVideo(it)
            }
        }
    }
}