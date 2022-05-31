package com.example.inpre.di

import com.example.data.FirebaseRepositoryImpl
import com.example.data.FlowerRepositoryImpl
import com.example.data.RetrofitCreator
import com.example.data.api.FirebaseApi
import com.example.data.converters.FirebaseFlowerToFlowerDomainConverter
import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.converters.FlowerDomainToFlowerDataConverter
import com.example.data.storage.AppDrawableFlowerStorage
import com.example.data.storage.FlowerStorage
import com.example.domain.repository.FirebaseRepository
import com.example.domain.repository.FlowerRepository
import com.google.gson.GsonBuilder
import org.koin.dsl.module

val dataModule = module {

    single<FlowerStorage> {
        AppDrawableFlowerStorage()
    }

    single<FlowerRepository> {
        FlowerRepositoryImpl(
            flowerStorage = get(),
            flowerDataToFlowerDomainConverter = get(),
            flowerDomainToFlowerDataConverter = get()
        )
    }

    factory {
        FlowerDataToFlowerDomainConverter()
    }

    factory {
        FlowerDomainToFlowerDataConverter()
    }

    factory {
        FirebaseFlowerToFlowerDomainConverter()
    }

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(get(), get())
    }

    single { GsonBuilder().serializeNulls().create() }
    val creator = RetrofitCreator()

    single { creator.createService(get(), FirebaseApi::class.java)}
}