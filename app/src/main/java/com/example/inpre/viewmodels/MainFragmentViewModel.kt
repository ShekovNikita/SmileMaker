package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase,
    private val addAllFlowersInDataUseCase: AddAllFlowersInDataUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            addAllFlowersInDataUseCase.execute(getFirebaseFlowerUseCase.execute())
            _basketLiveData.postValue(getAllFlowersFromDataUseCase.execute())
        }
    }

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