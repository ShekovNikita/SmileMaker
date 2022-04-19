package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.repository.usecases.GetAllFlowersUseCase

class MainFragmentViewModel(
    private val getAllFlowersUseCase: GetAllFlowersUseCase
) : ViewModel() {

    fun getAllFlowers() = getAllFlowersUseCase.execute()
}