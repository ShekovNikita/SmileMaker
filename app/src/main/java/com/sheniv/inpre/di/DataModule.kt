package com.sheniv.inpre.di

import com.sheniv.data.FirebaseRepositoryImpl
import com.sheniv.data.FlowerRepositoryImpl
import com.sheniv.data.RetrofitCreator
import com.sheniv.data.api.FirebaseApi
import com.sheniv.data.converters.ArrayFlowerDomainToArrayFlowerDataConverter
import com.sheniv.data.converters.FirebaseFlowerToFlowerDomainConverter
import com.sheniv.data.converters.FlowerDataToFlowerDomainConverter
import com.sheniv.data.converters.FlowerDomainToFlowerDataConverter
import com.sheniv.data.storage.AppDrawableFlowerStorage
import com.sheniv.data.storage.FlowerStorage
import com.sheniv.domain.repository.FirebaseRepository
import com.sheniv.domain.repository.FlowerRepository
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
            flowerDomainToFlowerDataConverter = get(),
            arrayFlowerDomainToArrayFlowerDataConverter = get()
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

    factory {
        ArrayFlowerDomainToArrayFlowerDataConverter()
    }

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(get(), get())
    }

    single { GsonBuilder().serializeNulls().create() }
    val creator = RetrofitCreator()

    single { creator.createService(get(), FirebaseApi::class.java)}
}