package com.example.data.storage.sourses

import com.example.data.storage.models.FlowerData

class Basket {

    private val basketFlower = arrayListOf<FlowerData>()

    fun addToBasket(flowerData: FlowerData) {
        println("---------------------------------addbasket before $basketFlower")
        var index = 0
        var unique = true
        if (basketFlower.size != 0) {
            for (i in basketFlower) {
                if (flowerData.title == i.title) {
                    index = basketFlower.indexOf(i)
                    unique = false
                    break
                } else continue
            }
            if (!unique) {
                basketFlower[index] = flowerData
            } else basketFlower.add(flowerData)
        } else basketFlower.add(flowerData)
        println("---------------------------------addbasket after $basketFlower")
    }

    fun getBasket(): ArrayList<FlowerData> {
        return ArrayList(basketFlower)
    }

    //fun getBasketSumma()
}