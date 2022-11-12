package com.sheniv.inpre.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.FirebaseApp
import com.google.firebase.appcheck.FirebaseAppCheck
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sheniv.inpre.firebase.ALL_FLOWERS_NODE
import com.sheniv.inpre.firebase.FLOWERS_NODE_CHILD
import com.sheniv.inpre.firebase.REF_DATABASE_ROOT
import com.sheniv.inpre.firebase.initFirebase
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenViewModel() : ViewModel() {

    private val _livedata = MutableLiveData<Any>()
    val livedata: LiveData<Any> = _livedata

    private val _basketLiveData = MutableLiveData<Boolean>()
    val basketLiveData: LiveData<Boolean> = _basketLiveData



    fun waitAndGoFather() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(1000)
            _livedata.postValue("getFirebaseFlowerUseCase.execute()")
        }
    }
}