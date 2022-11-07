package com.sheniv.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import com.sheniv.inpre.utilits.basket

class BasketFragmentViewModel() : ViewModel() {

    private val _resultLiveData = MutableLiveData<Int>()
    val resultLiveData: LiveData<Int> = _resultLiveData

    private val _basketLiveData = MutableLiveData<List<FlowerMain>>()
    val basketLiveData: LiveData<List<FlowerMain>> = _basketLiveData

    fun getBasket(): ArrayList<FlowerMain> {
        val baskets = arrayListOf<FlowerMain>()
        for (i in allFlowers) {
            if (i.amount > 0) {
                baskets.add(i)
            }
        }
        _resultLiveData.postValue(basket.getSumOfBasket())
        _basketLiveData.postValue(baskets)
        return baskets
    }

    fun deleteFlower(flower: FlowerMain) {
        basket.deleteFromBasket(flower)
        Log.e("delete", "$flower")
        getBasket()
    }

    fun changeAmount(flower: FlowerMain): ArrayList<FlowerMain> {
        val b = basket.changeAmountInBasket(flower)
        getBasket()
        return b
    }
}