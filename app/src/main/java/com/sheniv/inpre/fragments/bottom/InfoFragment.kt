package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentInfoBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInfoBinding.inflate(inflater, container, false)

    override fun FragmentInfoBinding.onBindView(savedInstanceState: Bundle?) {
    }

}