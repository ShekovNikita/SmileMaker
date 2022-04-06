package com.example.data.storage

import com.example.data.storage.models.FlowerData

interface FlowerStorage {

    fun getAllFlower(): ArrayList<FlowerData>

    fun getCategoryOfFlower(): ArrayList<FlowerData>
}