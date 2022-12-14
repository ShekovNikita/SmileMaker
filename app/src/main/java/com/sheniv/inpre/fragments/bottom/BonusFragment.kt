package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentBonusBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.bottomNavigationView
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.viewmodels.BonusFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BonusFragment : BaseFragment<FragmentBonusBinding>() {

    private val viewModel by viewModel<BonusFragmentViewModel>()

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
    ): FragmentBonusBinding = FragmentBonusBinding.inflate(inflater, container, false)

    override fun FragmentBonusBinding.onBindView(savedInstanceState: Bundle?) {

        bonusText.text = viewModel.getBonusText()
    }
}