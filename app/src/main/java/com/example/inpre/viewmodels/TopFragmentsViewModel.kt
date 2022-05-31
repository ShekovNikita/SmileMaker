package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Flower
import com.example.domain.repository.usecases.GetFirebaseFlowerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class TopFragmentsViewModel(
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    fun getRoses() {
        viewModelScope.launch(Dispatchers.Main) {
            val flowers = mutableListOf<Flower>()
            val flo = async { getFirebaseFlowerUseCase.execute() }
            for (i in flo.await()) {
                if (i.category == "розы")
                    flowers.add(i)
            }
            _basketLiveData.postValue(flowers as ArrayList<Flower>)
        }
    }

    fun getHit() {
        viewModelScope.launch(Dispatchers.Main) {
            val flowers = mutableListOf<Flower>()
            val flo = async { getFirebaseFlowerUseCase.execute() }
            for (i in flo.await()) {
                if (i.hit == "hit")
                    flowers.add(i)
            }
            _basketLiveData.postValue(flowers as ArrayList<Flower>)
        }
    }

    fun getHave() {
        viewModelScope.launch(Dispatchers.Main) {
            val flowers = mutableListOf<Flower>()
            val flo = async { getFirebaseFlowerUseCase.execute() }
            for (i in flo.await()) {
                if (i.have == "have")
                    flowers.add(i)
            }
            _basketLiveData.postValue(flowers as ArrayList<Flower>)
        }
    }

    fun getPioni() {
        viewModelScope.launch(Dispatchers.Main) {
            val flowers = mutableListOf<Flower>()
            val flo = async { getFirebaseFlowerUseCase.execute() }
            for (i in flo.await()) {
                if (i.category == "пионы")
                    flowers.add(i)
            }
            _basketLiveData.postValue(flowers as ArrayList<Flower>)
        }
    }

    fun getMix() {
        viewModelScope.launch(Dispatchers.Main) {
            val flowers = mutableListOf<Flower>()
            val flo = async { getFirebaseFlowerUseCase.execute() }
            for (i in flo.await()) {
                if (i.category == "микс")
                    flowers.add(i)
            }
            _basketLiveData.postValue(flowers as ArrayList<Flower>)
        }
    }
}