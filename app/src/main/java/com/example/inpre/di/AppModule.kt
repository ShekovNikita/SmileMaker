package com.example.inpre.di

import com.example.inpre.viewmodel.FlowersOnMainFragmentContainerViewModel
import com.example.inpre.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule =  module {

    viewModel<FlowersOnMainFragmentContainerViewModel> {
        FlowersOnMainFragmentContainerViewModel(getAllFlowersUseCase = get())
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getCategoryFlowersUseCase = get())
    }
}