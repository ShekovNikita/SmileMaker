package com.example.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _livedata = MutableLiveData<Any>()
    val livedata: LiveData<Any> = _livedata

    fun waitAndGoFather() {
        viewModelScope.launch {
            delay(1000)
            _livedata.postValue("Go")
        }
    }
}