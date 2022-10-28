package com.sheniv.data

import com.sheniv.data.converters.ArrayFlowerDomainToArrayFlowerDataConverter
import com.sheniv.data.converters.FlowerDataToFlowerDomainConverter
import com.sheniv.data.converters.FlowerDomainToFlowerDataConverter
import com.sheniv.data.storage.FlowerStorage
import com.sheniv.domain.model.Category
import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.FlowerRepository

class FlowerRepositoryImpl(
    private val flowerStorage: FlowerStorage,
    private val flowerDataToFlowerDomainConverter: FlowerDataToFlowerDomainConverter,
    private val flowerDomainToFlowerDataConverter: FlowerDomainToFlowerDataConverter,
    private val arrayFlowerDomainToArrayFlowerDataConverter: ArrayFlowerDomainToArrayFlowerDataConverter
) : FlowerRepository {

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return flowerStorage.getCategoryOfFlower()
    }

    override fun getBasket(): ArrayList<Flower> {
        return flowerDataToFlowerDomainConverter.invoke(flowerStorage.getBasket())
    }

    override fun getSumOfBasket(): Int {
        return flowerStorage.getSumOfBasket()
    }

    override fun getAllFlowersFromData(): ArrayList<Flower> {
        return flowerDataToFlowerDomainConverter.invoke(flowerStorage.getAllFlowersFromData())
    }

    override fun addAllFlowersInData(flowers: ArrayList<Flower>) {
            flowerStorage.addAllFlowersInData(arrayFlowerDomainToArrayFlowerDataConverter.invoke(flowers))
    }

    override fun changeFlowerInData(flower: Flower): ArrayList<Flower> {
        val b = flowerStorage.changeFlowerInData(flowerDomainToFlowerDataConverter.invoke(flower))
        return flowerDataToFlowerDomainConverter.invoke(b)
    }

    override fun postAmountValueNull(flower: Flower) {
        flowerStorage.postValueAmountNull(flowerDomainToFlowerDataConverter.invoke(flower))
    }
}