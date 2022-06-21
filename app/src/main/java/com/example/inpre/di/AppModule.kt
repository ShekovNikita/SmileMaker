package com.example.inpre.di

import com.example.inpre.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel(
            getAllFlowersFromDataUseCase = get(),
            postAmountValueNullUseCase = get(),
            changeFlowerInDataUseCase = get(),
            getFirebaseFlowerUseCase = get(),
            addAllFlowersInDataUseCase = get()
        )
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel(
            getCategoryFlowersUseCase = get(),
        )
    }

    viewModel<AboutFlowerActivityViewModel> {
        AboutFlowerActivityViewModel(
            changeFlowerInDataUseCase = get(),
            getBasketUseCase = get(),
            postAmountValueNullUseCase = get()
        )
    }

    viewModel<BasketFragmentViewModel> {
        BasketFragmentViewModel(
            changeFlowerInDataUseCase = get(),
            postAmountValueNullUseCase = get(),
            getSumOfBasketUseCase = get(),
            getAllFlowersFromDataUseCase = get()
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
        TopFragmentsViewModel(
            getFirebaseFlowerUseCase = get(),
            getAllFlowersFromDataUseCase = get(),
            postAmountValueNullUseCase = get(),
            changeFlowerInDataUseCase = get()
        )
    }

    viewModel<SplashScreenViewModel> {
        SplashScreenViewModel()
    }
}