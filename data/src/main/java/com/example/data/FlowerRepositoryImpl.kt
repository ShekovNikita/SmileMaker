package com.example.data

import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.converters.FlowerDomainToFlowerDataConverter
import com.example.data.storage.FlowerStorage
import com.example.domain.model.Category
import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class FlowerRepositoryImpl(
    private val flowerStorage: FlowerStorage,
    private val flowerDataToFlowerDomainConverter: FlowerDataToFlowerDomainConverter,
    private val flowerDomainToFlowerDataConverter: FlowerDomainToFlowerDataConverter
) : FlowerRepository {

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return flowerStorage.getCategoryOfFlower()
    }

    override fun addToBasket(flower: Flower) {
        flowerStorage.addToBasket(flowerDomainToFlowerDataConverter.invoke(flower))
    }

    override fun getBasket(): ArrayList<Flower> {
        return flowerDataToFlowerDomainConverter.invoke(flowerStorage.getBasket())
    }

    override fun changeAmountOfOneFlower(flower: Flower): ArrayList<Flower> {
        addToBasket(flower)
        return getBasket()
    }

    override fun deleteFromBasket(flower: Flower) {
        flowerStorage.deleteFromBasket(flowerDomainToFlowerDataConverter.invoke(flower))
    }

    override fun getSumOfBasket(): Int {
        return flowerStorage.getSumOfBasket()
    }
}