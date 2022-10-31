package com.sheniv.domain.repository

import com.sheniv.domain.model.Category
import com.sheniv.domain.model.FlowerMain

interface FlowerRepository {

    fun getCategoryOfFlower(): ArrayList<Category>

    fun getBasket(): ArrayList<FlowerMain>

    fun getSumOfBasket(): Int

    fun getAllFlowersFromData(): ArrayList<FlowerMain>

    fun addAllFlowersInData(flowers: ArrayList<FlowerMain>)

    fun changeFlowerInData(flower: FlowerMain): ArrayList<FlowerMain>

    fun postAmountValueNull(flower: FlowerMain)
}