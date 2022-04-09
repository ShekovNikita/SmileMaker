package com.example.data.storage.sourses

import com.example.data.storage.models.FlowerData

class Basket {

    private val basketFlower = mutableSetOf<FlowerData>()

    fun addToBasket(flowerData: FlowerData){
        basketFlower.add(flowerData)
        println("----------------------------------BASKET addtobasket   $basketFlower")
    }

    fun getBasket(): ArrayList<FlowerData>{
        println("----------------------------------BASKET GETbasket   $basketFlower")
        return ArrayList(basketFlower)
    }
}