package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.ui.AppBarConfiguration
import com.example.domain.model.Category
import com.example.domain.repository.usecases.GetCategoryFlowersUseCase
import com.example.inpre.R

class MainActivityViewModel(
    private val getCategoryFlowersUseCase: GetCategoryFlowersUseCase
) : ViewModel() {

    fun getCategory(): ArrayList<Category>{
        return getCategoryFlowersUseCase.execute()
    }

    fun getAppBarConfiguration(): AppBarConfiguration{
        return AppBarConfiguration(setOf(
            R.id.navigation_main,
            R.id.navigation_basket,
            R.id.navigation_contacts,
            R.id.navigation_bonus,
            R.id.navigation_delivery))
    }
}