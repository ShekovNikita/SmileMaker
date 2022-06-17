package com.example.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.ui.AppBarConfiguration
import com.example.domain.repository.usecases.AddAllFlowersInDataUseCase
import com.example.domain.repository.usecases.GetCategoryFlowersUseCase
import com.example.domain.repository.usecases.GetFirebaseFlowerUseCase
import com.example.inpre.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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