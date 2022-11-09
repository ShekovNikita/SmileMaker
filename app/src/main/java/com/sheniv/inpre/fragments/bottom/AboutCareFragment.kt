package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sheniv.inpre.R
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentAboutCareBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.bottomNavigationView
import com.sheniv.inpre.utilits.recyclerTop

class AboutCareFragment : BaseFragment<FragmentAboutCareBinding>() {

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
    ) = FragmentAboutCareBinding.inflate(inflater, container, false)

    override fun FragmentAboutCareBinding.onBindView(savedInstanceState: Bundle?) {
        aboutCareText.setText(R.string.about_care_new)
    }

}