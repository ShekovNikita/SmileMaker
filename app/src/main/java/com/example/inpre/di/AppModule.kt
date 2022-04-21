package com.example.inpre.di

import com.example.inpre.viewmodel.AboutFlowerActivityViewModel
import com.example.inpre.viewmodel.BasketFragmentViewModel
import com.example.inpre.viewmodel.MainActivityViewModel
import com.example.inpre.viewmodel.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(getAllFlowersUseCase = get())
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getCategoryFlowersUseCase = get())
    }

    viewModel<AboutFlowerActivityViewModel> {
        AboutFlowerActivityViewModel(addToBasketUseCase = get())
    }

    viewModel<BasketFragmentViewModel> {
        BasketFragmentViewModel(
            getBasketUseCase = get(),
            changeAmountOfOneFlowerUseCase = get(),
            deleteFlowerFromBasketUseCase = get()
        )
    }
}