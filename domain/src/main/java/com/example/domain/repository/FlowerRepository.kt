package com.example.domain.repository

import com.example.domain.model.Category
import com.example.domain.model.Flower

interface FlowerRepository {

    fun getCategoryOfFlower(): ArrayList<Category>

    fun getBasket(): ArrayList<Flower>

    fun getSumOfBasket(): Int

    fun getAllFlowersFromData(): ArrayList<Flower>

    fun addAllFlowersInData(flowers: ArrayList<Flower>)

    fun changeFlowerInData(flower: Flower): ArrayList<Flower>

    fun postAmountValueNull(flower: Flower)
}