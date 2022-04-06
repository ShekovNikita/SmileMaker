package com.example.inpre

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.data.FlowerRepositoryImpl
import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.storage.AppDrawableFlowerStorage
import com.example.inpre.databinding.FragmentFlowersOnMainContainerBinding
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.GetAllFlowersUseCase

class FlowersOnMainFragmentContainer : BaseFragment<FragmentFlowersOnMainContainerBinding>(),
    MainFlowerClick {

    private val flowerStorage by lazy {
        AppDrawableFlowerStorage(
            requireActivity().applicationContext
        )
    }
    private val flowerRepository by lazy {
        FlowerRepositoryImpl(
            flowerStorage,
            FlowerDataToFlowerDomainConverter()
        )
    }
    private val getAllFlowersUseCase by lazy { GetAllFlowersUseCase(flowerRepository) }


    val mainFlowerAdapter = MainFlowerAdapter(this)
    var flowerList = ArrayList<Flower>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFlowersOnMainContainerBinding =
        FragmentFlowersOnMainContainerBinding.inflate(inflater, container, false)

    override fun FragmentFlowersOnMainContainerBinding.onBindView(savedInstanceState: Bundle?) {

        recyclerFlowersOnMain.adapter = mainFlowerAdapter
        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 3)

        flowerList = getAllFlowersUseCase.execute() as ArrayList<Flower>

        mainFlowerAdapter.addCategory(flowerList)
    }

    override fun sendData(flower: Flower) {
        println("-------------------------senddata    $flower")
        setFragmentResult("1", bundleOf("flower" to flower))
        navController.navigate(FlowersOnMainFragmentContainerDirections.actionFlowersOnMainFragmentContainerToAboutOneFlowerFragment())
    }

}