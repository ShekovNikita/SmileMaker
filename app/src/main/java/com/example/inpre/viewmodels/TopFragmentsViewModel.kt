package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.example.domain.repository.usecases.GetAllFlowersFromDataUseCase
import com.example.domain.repository.usecases.GetFirebaseFlowerUseCase
import com.example.domain.repository.usecases.PostAmountValueNullUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TopFragmentsViewModel(
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase,
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    fun getRoses() {
        val flowers = mutableListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.category == "розы")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<Flower>)
    }

    fun getHit() {
        val flowers = mutableListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.hit == "hit")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<Flower>)
    }

    fun getHave() {
        val flowers = mutableListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.have == "have")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<Flower>)
    }

    fun getPioni() {
        val flowers = mutableListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.category == "пионы")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<Flower>)
    }

    fun getMix() {
        val flowers = mutableListOf<Flower>()
        for (i in getAllFlowersFromDataUseCase.execute()){
            if (i.category == "микс")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<Flower>)
    }

    fun deleteFlower(flower: Flower) {
        postAmountValueNullUseCase.execute(flower)
    }

    fun changeAmount(flower: Flower): ArrayList<Flower> {
        return changeFlowerInDataUseCase.execute(flower)
    }
}