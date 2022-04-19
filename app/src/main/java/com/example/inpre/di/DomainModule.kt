package com.example.inpre.di

import com.example.domain.repository.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllFlowersUseCase> {
        GetAllFlowersUseCase(flowerRepository = get())
    }

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
}