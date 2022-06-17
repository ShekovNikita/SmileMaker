package com.example.data.storage

import com.example.data.storage.models.FlowerData
import com.example.data.storage.sourses.AllFlowersData
import com.example.data.storage.sourses.FlowerCategory
import com.example.domain.model.Category

class AppDrawableFlowerStorage : FlowerStorage {

    private val allFlowerData = AllFlowersData()

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return FlowerCategory().arrayFlowerCategory()
    }

    override fun getBasket(): ArrayList<FlowerData> {
        return allFlowerData.getBasket()
    }

    override fun getSumOfBasket(): Int {
        return allFlowerData.getSumOfBasket()
    }

    override fun getAllFlowersFromData(): ArrayList<FlowerData> {
        return allFlowerData.getAllFlowersFromData()
    }

    override fun addAllFlowersInData(flowers: ArrayList<FlowerData>) {
        allFlowerData.addAllFlowerInData(flowers)
    }

    override fun changeFlowerInData(flowerData: FlowerData): ArrayList<FlowerData> {
        return allFlowerData.changeFlowerInData(flowerData)
    }

    override fun postValueAmountNull(flowerData: FlowerData){
        return allFlowerData.postAmountValueNull(flowerData)
    }
}
