package com.example.inpre.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentMainBinding
import com.example.domain.model.Flower
import com.example.inpre.AboutFlowerActivity
import com.example.inpre.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(),
    MainFlowerClick {

    private val viewModelMain by viewModel<MainFragmentViewModel>()

    private val mainFlowerAdapter by lazy {MainFlowerAdapter(this, viewModelMain.getAllFlowers())}

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)


    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {
        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 3)
        recyclerFlowersOnMain.adapter = mainFlowerAdapter
    }

    override fun sendData(flower: Flower) {
        activity?.let{
            val intent = Intent (it, AboutFlowerActivity::class.java)
            intent.putExtra("flower", flower)
            it.startActivity(intent)
        }
    }
}