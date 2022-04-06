package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Category
import com.example.domain.repository.usecases.GetCategoryFlowersUseCase

class MainActivityViewModel(
    private val getCategoryFlowersUseCase: GetCategoryFlowersUseCase
) : ViewModel() {

    fun getCategory(): ArrayList<Category>{
        return getCategoryFlowersUseCase.execute()
    }
}