package com.example.data

import com.example.data.converters.FlowerDataToFlowerDomainConverter
import com.example.data.storage.FlowerStorage
import com.example.domain.model.Category
import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class FlowerRepositoryImpl(
    private val flowerStorage: FlowerStorage,
    private val flowerDataToFlowerDomainConverter: FlowerDataToFlowerDomainConverter
) : FlowerRepository {

    override fun getAllFlower(): ArrayList<Flower> {
        return flowerDataToFlowerDomainConverter.invoke(flowerStorage.getAllFlower())
    }

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return flowerStorage.getCategoryOfFlower()
    }
}