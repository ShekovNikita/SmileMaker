package com.example.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.ChangeAmountOfOneFlowerUseCase
import com.example.domain.repository.usecases.DeleteFlowerFromBasketUseCase
import com.example.domain.repository.usecases.GetFirebaseFlowerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase,
    private val changeAmountOfOneFlowerUseCase: ChangeAmountOfOneFlowerUseCase,
    private val deleteFlowerFromBasketUseCase: DeleteFlowerFromBasketUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    val flowers = mutableListOf<Flower>()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val flo = async { getFirebaseFlowerUseCase.execute() }
            flowers.addAll(flo.await())
            _basketLiveData.postValue(flo.await())
            Log.e("sdf", "INIt")
        }
    }

    fun sorted(){
        flowers.sortBy { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<Flower>?)
    }

    fun deleteFlower(flower: Flower) {
        deleteFlowerFromBasketUseCase.execute(flower)
    }

    fun changeAmount(flower: Flower): ArrayList<Flower> {
        val b = changeAmountOfOneFlowerUseCase.execute(flower)
        return b
    }

    override fun onCleared() {
        Log.e("onClered", "clearesd")
        super.onCleared()
    }
}