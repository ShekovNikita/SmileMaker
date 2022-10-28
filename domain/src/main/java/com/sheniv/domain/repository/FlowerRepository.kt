package com.sheniv.domain.repository

import com.sheniv.domain.model.Category
import com.sheniv.domain.model.Flower

interface FlowerRepository {

    fun getCategoryOfFlower(): ArrayList<Category>

    fun getBasket(): ArrayList<Flower>

    fun getSumOfBasket(): Int

    fun getAllFlowersFromData(): ArrayList<Flower>

    fun addAllFlowersInData(flowers: ArrayList<Flower>)

    fun changeFlowerInData(flower: Flower): ArrayList<Flower>

    fun postAmountValueNull(flower: Flower)
}