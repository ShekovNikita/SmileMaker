package com.example.data.storage

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Category

interface FlowerStorage {

    fun getAllFlower(): ArrayList<FlowerData>

    fun getCategoryOfFlower(): ArrayList<Category>
}