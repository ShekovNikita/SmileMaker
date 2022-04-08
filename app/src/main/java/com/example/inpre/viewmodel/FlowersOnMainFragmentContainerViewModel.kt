package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.GetAllFlowersUseCase

class FlowersOnMainFragmentContainerViewModel(
    private val getAllFlowersUseCase: GetAllFlowersUseCase
): ViewModel() {

    fun getAllFlowers(): ArrayList<Flower>{
        return getAllFlowersUseCase.execute()
    }


}