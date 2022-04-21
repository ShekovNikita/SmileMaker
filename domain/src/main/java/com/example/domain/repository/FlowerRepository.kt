package com.example.domain.repository

import com.example.domain.model.Category
import com.example.domain.model.Flower

interface FlowerRepository {

    fun getAllFlower(): ArrayList<Flower>

    fun getCategoryOfFlower(): ArrayList<Category>

    fun addToBasket(flower: Flower)

    fun getBasket(): ArrayList<Flower>

    fun changeAmountOfOneFlower(flower: Flower): ArrayList<Flower>

    fun deleteFromBasket(flower: Flower)
}