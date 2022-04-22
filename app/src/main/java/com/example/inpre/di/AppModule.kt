package com.example.inpre.di

import com.example.inpre.viewmodel.*
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
    viewModel<DataAboutBuyerViewModel>{
        DataAboutBuyerViewModel(getBasketUseCase = get())
    }
}