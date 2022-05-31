package com.example.data.storage.sourses

import com.example.data.storage.models.FlowerData

class Basket {

    private val basketFlower = arrayListOf<FlowerData>()

    fun addToBasket(flowerData: FlowerData) {
        var index = 0
        var unique = true
        if (basketFlower.size != 0) {
            for (i in basketFlower) {
                if (flowerData.articul == i.articul) {
                    index = basketFlower.indexOf(i)
                    unique = false
                    break
                } else continue
            }
            if (!unique) {
                basketFlower[index] = flowerData
            } else basketFlower.add(flowerData)
        } else basketFlower.add(flowerData)
    }

    fun getBasket(): ArrayList<FlowerData> {
        return ArrayList(basketFlower)
    }

    fun deleteFromBasket(flowerData: FlowerData) {
        basketFlower.remove(flowerData)
    }

    fun getSumOfBasket(): Int {
        var summa = 0
        for (i in basketFlower) {
            summa += i.cost.toInt() * i.amount
        }
        return summa
    }
}