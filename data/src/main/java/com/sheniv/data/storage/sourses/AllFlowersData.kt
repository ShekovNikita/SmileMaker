package com.sheniv.data.storage.sourses

import com.sheniv.data.storage.models.FlowerData
import com.sheniv.domain.model.Flower

class AllFlowersData {

    private val allFlowers = arrayListOf<FlowerData>()

    fun getAllFlowersFromData() = allFlowers

    fun addAllFlowerInData(flowers: ArrayList<FlowerData>){
        if (allFlowers.size == 0) {
            allFlowers.addAll(flowers)
        }
    }

    fun postAmountValueNull(flowerData: FlowerData){
        var d = 0
        for ((index,i) in allFlowers.withIndex()){
            if (i.articul == flowerData.articul){
                d = index
            }
        }
        allFlowers[d] = flowerData
    }

    fun changeFlowerInData(flowerData: FlowerData): ArrayList<FlowerData> {
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

    fun getBasket(): ArrayList<FlowerData> {
        val basket = arrayListOf<FlowerData>()
        for (i in allFlowers){
            if (i.amount > 0 ){
                basket.add(i)
            }
        }
        return basket
    }
}