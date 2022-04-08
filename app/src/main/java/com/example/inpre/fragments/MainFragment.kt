package com.example.inpre.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentMainBinding
import com.example.domain.model.Flower
import com.example.inpre.viewmodel.FlowersOnMainFragmentContainerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(),
    MainFlowerClick {

    private val viewModelMain by viewModel<FlowersOnMainFragmentContainerViewModel>()

    private val mainFlowerAdapter = MainFlowerAdapter(this)

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)


    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {
        recyclerFlowersOnMain.adapter = mainFlowerAdapter
        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 3)
        mainFlowerAdapter.addCategory(viewModelMain.getAllFlowers())
    }

    override fun sendData(flower: Flower) {
        setFragmentResult("1", bundleOf("flower" to flower))
        navController.navigate(MainFragmentDirections.actionMainFragmentToAboutOneFlowerFragment())
    }
}