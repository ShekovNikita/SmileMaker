package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel
import androidx.navigation.ui.AppBarConfiguration
import com.sheniv.domain.repository.usecases.GetCategoryFlowersUseCase
import com.sheniv.inpre.R

class MainActivityViewModel(
    private val getCategoryFlowersUseCase: GetCategoryFlowersUseCase,
) : ViewModel() {

    fun getCategory() = getCategoryFlowersUseCase.execute()

    fun getAppBarConfiguration(): AppBarConfiguration {
        return AppBarConfiguration(
            setOf(
                R.id.navigation_main,
                R.id.navigation_basket,
                R.id.navigation_contacts,
                R.id.navigation_bonus,
                R.id.navigation_delivery
            )
        )
    }
}