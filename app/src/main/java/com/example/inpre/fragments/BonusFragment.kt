package com.example.inpre.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentBonusBinding

class BonusFragment : BaseFragment<FragmentBonusBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBonusBinding = FragmentBonusBinding.inflate(inflater, container, false)

    override fun FragmentBonusBinding.onBindView(savedInstanceState: Bundle?) {
    }
}