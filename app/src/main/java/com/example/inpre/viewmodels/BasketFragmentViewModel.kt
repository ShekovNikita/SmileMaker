package com.example.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.*

class BasketFragmentViewModel(
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val getSumOfBasketUseCase: GetSumOfBasketUseCase,
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase
) : ViewModel() {

    private val _resultLiveData = MutableLiveData<Int>()
    val resultLiveData: LiveData<Int> = _resultLiveData

    private val _basketLiveData = MutableLiveData<List<Flower>>()
    val basketLiveData: LiveData<List<Flower>> = _basketLiveData

    fun getBasket(): ArrayList<Flower> {
        val basket = arrayListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.amount > 0){
                basket.add(i)
            }
        }
        _resultLiveData.postValue(getSumOfBasketUseCase.execute())
        _basketLiveData.postValue(basket)
        return basket
    }

    fun deleteFlower(flower: Flower) {
        postAmountValueNullUseCase.execute(flower)
        Log.e("delete", "$flower")
        getBasket()
    }

    fun changeAmount(flower: Flower): ArrayList<Flower> {
        val b = changeFlowerInDataUseCase.execute(flower)
        getBasket()
        return b
    }
}