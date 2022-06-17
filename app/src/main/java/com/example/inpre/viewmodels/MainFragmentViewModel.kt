package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.*

class MainFragmentViewModel(
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    fun getAllFlowers(){
        _basketLiveData.postValue(getAllFlowersFromDataUseCase.execute())
    }

    fun sorted(){
        val flowers = mutableListOf<Flower>()
        flowers.addAll(getAllFlowersFromDataUseCase.execute())
        flowers.sortBy { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<Flower>?)
    }

    fun deleteFlower(flower: Flower) {
        postAmountValueNullUseCase.execute(flower)
    }

    fun changeAmount(flower: Flower): ArrayList<Flower> {
        return changeFlowerInDataUseCase.execute(flower)
    }
}