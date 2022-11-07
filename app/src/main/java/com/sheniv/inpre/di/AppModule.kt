package com.sheniv.inpre.di

import com.sheniv.inpre.viewmodels.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<MainFragmentViewModel> {
        MainFragmentViewModel()
    }

    viewModel<MainActivityViewModel> {
        MainActivityViewModel()
    }

    viewModel<AboutFlowerActivityViewModel> {
        AboutFlowerActivityViewModel()
    }

    viewModel<BasketFragmentViewModel> {
        BasketFragmentViewModel()
    }

    viewModel<DataAboutBuyerViewModel> {
        DataAboutBuyerViewModel()
    }

    viewModel<BonusFragmentViewModel> {
        BonusFragmentViewModel()
    }

    viewModel<DeliveryFragmentViewModel> {
        DeliveryFragmentViewModel()
    }

    viewModel<TopFragmentsViewModel> {
        TopFragmentsViewModel()
    }

    viewModel<SplashScreenViewModel> {
        SplashScreenViewModel()
    }
}