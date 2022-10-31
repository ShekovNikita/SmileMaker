package com.sheniv.inpre.basket

import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers


class Basket {

    fun deleteFromBasket(flowerData: FlowerMain){
        var d = 0
        for ((index,i) in allFlowers.withIndex()){
            if (i.articul == flowerData.articul){
                d = index
            }
        }
        flowerData.amount = 0
        allFlowers[d] = flowerData
    }

    fun changeAmountInBasket(flowerData: FlowerMain): ArrayList<FlowerMain> {
        var d = 0
        for ((index,i) in allFlowers.withIndex()){
            if (i.articul == flowerData.articul){
                d = index
            }
        }
        allFlowers[d] = flowerData
        return allFlowers
    }

    fun getSumOfBasket(): Int {
        var summa = 0
        for (i in allFlowers) {
            if (i.amount > 0)
                summa += i.cost.toInt() * i.amount
        }
        return summa
    }

    fun getBasket(): ArrayList<FlowerMain> {
        val basket = arrayListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.amount > 0 ){
                basket.add(i)
            }
        }
        return basket
    }
}