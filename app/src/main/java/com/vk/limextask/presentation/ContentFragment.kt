package com.vk.limextask.presentation

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.vk.limextask.R
import com.vk.limextask.databinding.FragmentContainerChannelBinding
import com.vk.limextask.presentation.adapter.ViewPagerAdapter

class ContentFragment : Fragment() {
    private var _binding: FragmentContainerChannelBinding? = null
    private val binding: FragmentContainerChannelBinding
        get() = _binding ?: throw  RuntimeException("FragmentContainerChannelBinding is null")

    private lateinit var adapter : ViewPagerAdapter
    private lateinit var viewPager : ViewPager2

    private val fragmentList = listOf(
        AllChannelsListFragment(),
        FavoriteChannelsListFragment()
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContainerChannelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onStart() {
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        super.onStart()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    private fun initViewPager(){
        adapter = ViewPagerAdapter(activity as AppCompatActivity, fragmentList)
        viewPager = binding.channelViewPager
        viewPager.adapter = adapter
        val tabNames : Array<String> = arrayOf(
            resources.getString(R.string.tab_title_all),
            resources.getString(R.string.tab_title_favorites)
        )
        TabLayoutMediator(binding.tabLayout, binding.channelViewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }
}