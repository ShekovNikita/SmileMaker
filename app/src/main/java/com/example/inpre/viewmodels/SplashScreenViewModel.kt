package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.usecases.AddAllFlowersInDataUseCase
import com.example.domain.repository.usecases.GetFirebaseFlowerUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase,
    private val addAllFlowersInDataUseCase: AddAllFlowersInDataUseCase
) : ViewModel() {

    private val _livedata = MutableLiveData<Any>()
    val livedata: LiveData<Any> = _livedata

    fun waitAndGoFather() {
        viewModelScope.launch(Dispatchers.Main) {
            val flo = async { getFirebaseFlowerUseCase.execute() }
            addAllFlowersInDataUseCase.execute(flo.await())
            _livedata.postValue(getFirebaseFlowerUseCase.execute())
        }
    }
}