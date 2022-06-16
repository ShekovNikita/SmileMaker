package com.example.inpre.di

import com.example.inpre.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            getFirebaseFlowerUseCase = get(),
            changeAmountOfOneFlowerUseCase = get(),
            deleteFlowerFromBasketUseCase = get()
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(getCategoryFlowersUseCase = get())
    }

    viewModel<AboutFlowerActivityViewModel> {
        AboutFlowerActivityViewModel(
            addToBasketUseCase = get(),
            getBasketUseCase = get()
        )
    }

    viewModel<BasketFragmentViewModel> {
        BasketFragmentViewModel(
            getBasketUseCase = get(),
            changeAmountOfOneFlowerUseCase = get(),
            deleteFlowerFromBasketUseCase = get(),
            getSumOfBasketUseCase = get()
        )
    }
    viewModel<DataAboutBuyerViewModel> {
        DataAboutBuyerViewModel(
            getBasketUseCase = get(),
            getSumOfBasketUseCase = get()
        )
    }

    viewModel<BonusFragmentViewModel> {
        BonusFragmentViewModel()
    }

    viewModel<DeliveryFragmentViewModel> {
        DeliveryFragmentViewModel()
    }

    viewModel<TopFragmentsViewModel> {
        TopFragmentsViewModel(getFirebaseFlowerUseCase = get())
    }

    viewModel<SplashScreenViewModel> {
        SplashScreenViewModel()
    }
}