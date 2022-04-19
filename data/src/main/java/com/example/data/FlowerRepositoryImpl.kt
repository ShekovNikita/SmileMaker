package com.example.data

import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.converters.FlowerDomainToFlowerDataConverter
import com.example.data.storage.FlowerStorage
import com.example.data.storage.models.FlowerData
import com.example.domain.model.Category
import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class FlowerRepositoryImpl(
    private val flowerStorage: FlowerStorage,
    private val flowerDataToFlowerDomainConverter: FlowerDataToFlowerDomainConverter,
    private val flowerDomainToFlowerDataConverter: FlowerDomainToFlowerDataConverter
) : FlowerRepository {

    override fun getAllFlower(): ArrayList<Flower> {
        return flowerDataToFlowerDomainConverter.invoke(flowerStorage.getAllFlower())
    }

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return flowerStorage.getCategoryOfFlower()
    }

    override fun addToBasket(flower: Flower) {
        flowerStorage.addToBasket(flowerDomainToFlowerDataConverter.invoke(flower))
    }

    override fun getBasket(): ArrayList<Flower> {
        return flowerStorage.getBasket()
    }

    override fun changeAmountOfOneFlower(flower: Flower): ArrayList<Flower> {
        addToBasket(flower)
        return getBasket()
    }
}