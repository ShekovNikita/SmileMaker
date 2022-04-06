package com.example.inpre.di

import com.example.domain.repository.usecases.GetAllFlowersUseCase
import com.example.domain.repository.usecases.GetCategoryFlowersUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetAllFlowersUseCase> {
        GetAllFlowersUseCase(flowerRepository = get())
    }

    factory<GetCategoryFlowersUseCase> {
        GetCategoryFlowersUseCase(flowerRepository = get())
    }
}