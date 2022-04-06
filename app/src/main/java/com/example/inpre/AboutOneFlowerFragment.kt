package com.example.inpre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentAboutOneFlowerBinding
import com.example.domain.model.Flower


class AboutOneFlowerFragment : BaseFragment<FragmentAboutOneFlowerBinding>() {

    lateinit var getFlower: Flower

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAboutOneFlowerBinding =
        FragmentAboutOneFlowerBinding.inflate(inflater, container, false)

    override fun FragmentAboutOneFlowerBinding.onBindView(savedInstanceState: Bundle?) {
        setFragmentResultListener("1") { _, bundle ->
            getFlower = bundle.getSerializable("flower") as Flower
            if (getFlower != null) {
                imageFlower.setImageResource(getFlower.image)
                imageFlower.setImageResource(getFlower.image)
                cost.text = "${getFlower.cost} BYN"
                textFlower.text = getFlower.title
            }
        }
    }
}