package com.sheniv.inpre.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentStateAdapter(activity: FragmentActivity, private val topFragment: List<Fragment>) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = topFragment.size

    override fun createFragment(position: Int): Fragment = topFragment[position]

}