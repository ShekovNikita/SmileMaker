package com.sheniv.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sheniv.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.sheniv.domain.repository.usecases.GetAllFlowersFromDataUseCase
import com.sheniv.domain.repository.usecases.GetSumOfBasketUseCase
import com.sheniv.domain.repository.usecases.PostAmountValueNullUseCase
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import com.sheniv.inpre.utilits.basket

class BasketFragmentViewModel(
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val getSumOfBasketUseCase: GetSumOfBasketUseCase,
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase
) : ViewModel() {

    private val _resultLiveData = MutableLiveData<Int>()
    val resultLiveData: LiveData<Int> = _resultLiveData

    private val _basketLiveData = MutableLiveData<List<FlowerMain>>()
    val basketLiveData: LiveData<List<FlowerMain>> = _basketLiveData

    fun getBasket(): ArrayList<FlowerMain> {
        val baskets = arrayListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.amount > 0){
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