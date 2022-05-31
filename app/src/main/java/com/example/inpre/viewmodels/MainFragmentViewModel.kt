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

class MainFragmentViewModel(
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<Flower>>()
    val basketLiveData: LiveData<ArrayList<Flower>> = _basketLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val flo = async { getFirebaseFlowerUseCase.execute() }
            _basketLiveData.postValue(flo.await())
        }
    }
}