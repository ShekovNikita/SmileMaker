package com.example.inpre.di

import com.example.domain.repository.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetCategoryFlowersUseCase> {
        GetCategoryFlowersUseCase(flowerRepository = get())
    }

    factory<AddToBasketUseCase> {
        AddToBasketUseCase(flowerRepository = get())
    }

    factory<GetBasketUseCase> {
        GetBasketUseCase(flowerRepository = get())
    }

    factory<ChangeAmountOfOneFlowerUseCase> {
        ChangeAmountOfOneFlowerUseCase(flowerRepository = get())
    }

    factory<DeleteFlowerFromBasketUseCase> {
        DeleteFlowerFromBasketUseCase(flowerRepository = get())
    }

    factory<GetSumOfBasketUseCase> {
        GetSumOfBasketUseCase(flowerRepository = get())
    }

    factory {
        GetFirebaseFlowerUseCase(firebaseRepository = get())
    }
}