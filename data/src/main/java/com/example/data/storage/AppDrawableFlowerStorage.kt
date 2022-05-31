package com.example.data.storage

import com.example.data.storage.models.FlowerData
import com.example.data.storage.sourses.Basket
import com.example.data.storage.sourses.FlowerCategory
import com.example.domain.model.Category

class AppDrawableFlowerStorage() : FlowerStorage {

    private val basket = Basket()

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return FlowerCategory().arrayFlowerCategory()
    }

    override fun addToBasket(flowerData: FlowerData) {
        basket.addToBasket(flowerData)
    }

    override fun getBasket(): ArrayList<FlowerData> {
        return basket.getBasket()
    }

    override fun changeAmountOfOneFlower(flowerData: FlowerData): ArrayList<FlowerData> {
        addToBasket(flowerData)
        return getBasket()
    }

    override fun deleteFromBasket(flowerData: FlowerData) {
        basket.deleteFromBasket(flowerData)
    }

    override fun getSumOfBasket(): Int {
        return basket.getSumOfBasket()
    }

}
