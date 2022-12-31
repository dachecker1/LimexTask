package com.vk.limextask.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.vk.limextask.presentation.ARG_OBJECT
import com.vk.limextask.presentation.ChannelListFragment
import com.vk.limextask.presentation.TabSelectorFragment

class ChannelListViewPagerAdapter(fragment : ChannelListFragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = TabSelectorFragment()
        fragment.arguments = Bundle().apply {
            putInt(ARG_OBJECT, position + 1)
        }
        return fragment
    }
}