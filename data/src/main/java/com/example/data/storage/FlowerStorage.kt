package com.example.data.storage

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Category
import com.example.domain.model.Flower

interface FlowerStorage {

    fun getAllFlower(): ArrayList<FlowerData>

    fun getCategoryOfFlower(): ArrayList<Category>

    fun addToBasket(flowerData: FlowerData)

    fun getBasket(): ArrayList<Flower>
}