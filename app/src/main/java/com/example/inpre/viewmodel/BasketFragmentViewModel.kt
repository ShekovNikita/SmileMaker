package com.example.inpre.viewmodel

import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.GetBasketUseCase

class BasketFragmentViewModel(private val getBasketUseCase: GetBasketUseCase) : ViewModel(){

    fun getBasket(): ArrayList<Flower>{
        return getBasketUseCase.execute()
    }
}