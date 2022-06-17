package com.example.inpre.di

import com.example.domain.repository.usecases.*
import org.koin.dsl.module

val domainModule = module {

    factory<GetCategoryFlowersUseCase> {
        GetCategoryFlowersUseCase(flowerRepository = get())
    }

    factory<GetBasketUseCase> {
        GetBasketUseCase(flowerRepository = get())
    }

    factory<GetSumOfBasketUseCase> {
        GetSumOfBasketUseCase(flowerRepository = get())
    }

    factory {
        GetFirebaseFlowerUseCase(firebaseRepository = get())
    }

    factory<AddAllFlowersInDataUseCase> {
        AddAllFlowersInDataUseCase(flowerRepository = get())
    }

    factory<GetAllFlowersFromDataUseCase> {
        GetAllFlowersFromDataUseCase(flowerRepository = get())
    }

    factory<PostAmountValueNullUseCase> {
        PostAmountValueNullUseCase(flowerRepository = get())
    }

    factory<ChangeFlowerInDataUseCase> {
        ChangeFlowerInDataUseCase(flowerRepository = get())
    }
}