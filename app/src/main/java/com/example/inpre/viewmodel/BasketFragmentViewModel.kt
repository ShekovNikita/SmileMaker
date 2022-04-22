package com.example.inpre.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeAmountOfOneFlowerUseCase
import com.example.domain.repository.usecases.DeleteFlowerFromBasketUseCase
import com.example.domain.repository.usecases.GetBasketUseCase

class BasketFragmentViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val changeAmountOfOneFlowerUseCase: ChangeAmountOfOneFlowerUseCase,
    private val deleteFlowerFromBasketUseCase: DeleteFlowerFromBasketUseCase
) : ViewModel() {

    private val _resultLiveData = MutableLiveData<Int>()
    val resultLiveData: LiveData<Int> = _resultLiveData

    private val _basketLiveData = MutableLiveData<List<Flower>>()
    val basketLiveData: LiveData<List<Flower>> = _basketLiveData

    fun getBasket(): ArrayList<Flower> {
        val basket = getBasketUseCase.execute()
        _resultLiveData.postValue(getSum(basket))
        return basket
    }

    fun deleteFlower(flower: Flower) {
        deleteFlowerFromBasketUseCase.execute(flower)
        _basketLiveData.postValue(getBasket())
        println("-------------------${getBasket()}")
    }

    fun changeAmount(flower: Flower): ArrayList<Flower>{
        val b = changeAmountOfOneFlowerUseCase.execute(flower)
        getBasket()
        return b
    }

    private fun getSum(flowers: ArrayList<Flower>): Int{
        var summa = 0
        for (i in flowers){
            summa += i.cost.toInt() * i.amount
        }
        return summa
    }
}