package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeAmountOfOneFlowerUseCase
import com.example.domain.repository.usecases.DeleteFlowerFromBasketUseCase
import com.example.domain.repository.usecases.GetBasketUseCase
import com.example.domain.repository.usecases.GetSumOfBasketUseCase

class BasketFragmentViewModel(
    private val getBasketUseCase: GetBasketUseCase,
    private val changeAmountOfOneFlowerUseCase: ChangeAmountOfOneFlowerUseCase,
    private val deleteFlowerFromBasketUseCase: DeleteFlowerFromBasketUseCase,
    private val getSumOfBasketUseCase: GetSumOfBasketUseCase
) : ViewModel() {

    private val _resultLiveData = MutableLiveData<Int>()
    val resultLiveData: LiveData<Int> = _resultLiveData

    private val _basketLiveData = MutableLiveData<List<Flower>>()
    val basketLiveData: LiveData<List<Flower>> = _basketLiveData

    fun getBasket(): ArrayList<Flower> {
        val basket = getBasketUseCase.execute()
        _resultLiveData.postValue(getSumOfBasketUseCase.execute())
        _basketLiveData.postValue(basket)
        return basket
    }

    fun deleteFlower(flower: Flower) {
        deleteFlowerFromBasketUseCase.execute(flower)
        getBasket()
    }

    fun changeAmount(flower: Flower): ArrayList<Flower> {
        val b = changeAmountOfOneFlowerUseCase.execute(flower)
        getBasket()
        return b
    }
}