package com.sheniv.inpre.fragments.add_flower

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sheniv.inpre.R
import com.sheniv.inpre.adapter.FragmentStateAdapter
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentChangeFlowerBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.bottomNavigationView
import com.sheniv.inpre.utilits.recyclerTop

class ChangeFlowerFragment : BaseFragment<FragmentChangeFlowerBinding>() {

    private val topFragment = listOf<Fragment>(AddFlowerFragment(), AllFlowersChangeFragment())
    private val tabNames = listOf<String>("Новый", "Изменить")

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
        bottomNavigationView.beGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
        bottomNavigationView.beVisible()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeFlowerBinding.inflate(inflater, container, false)

    override fun FragmentChangeFlowerBinding.onBindView(savedInstanceState: Bundle?) {

        val tabLayout = requireView().findViewById<TabLayout>(R.id.tab_layout)

        viewPager2.adapter = FragmentStateAdapter(requireActivity(), topFragment)
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
    }

}