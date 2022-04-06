package com.example.inpre.di

import com.example.data.FlowerRepositoryImpl
import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.storage.AppDrawableFlowerStorage
import com.example.data.storage.FlowerStorage
import com.example.domain.repository.FlowerRepository
import org.koin.dsl.module

val dataModule = module {

    single<FlowerStorage> {
        AppDrawableFlowerStorage(context = get())
    }

    single<FlowerRepository> {
        FlowerRepositoryImpl(
            flowerStorage = get(),
            flowerDataToFlowerDomainConverter = get()
        )
    }

    factory {
        FlowerDataToFlowerDomainConverter()
    }
}