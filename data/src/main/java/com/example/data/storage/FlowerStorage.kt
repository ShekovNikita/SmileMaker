package com.example.data.storage

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Category

interface FlowerStorage {

    fun getCategoryOfFlower(): ArrayList<Category>

    fun getBasket(): ArrayList<FlowerData>

    fun getSumOfBasket(): Int

    fun getAllFlowersFromData(): ArrayList<FlowerData>

    fun addAllFlowersInData(flowers: ArrayList<FlowerData>)

    fun changeFlowerInData(flowerData: FlowerData): ArrayList<FlowerData>

    fun postValueAmountNull(flowerData: FlowerData)
}