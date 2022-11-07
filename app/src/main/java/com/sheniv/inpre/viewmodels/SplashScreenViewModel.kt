package com.sheniv.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

class SplashScreenViewModel : ViewModel() {

    private val _livedata = MutableLiveData<Any>()
    val livedata: LiveData<Any> = _livedata

    private val _basketLiveData = MutableLiveData<Boolean>()
    val basketLiveData: LiveData<Boolean> = _basketLiveData

    init {
        initFirebase()
        viewModelScope.launch(Dispatchers.Main) {
            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE).child(FLOWERS_NODE_CHILD)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        allFlowers.clear()
                        if (snapshot.exists()) {
                            for (favorite in snapshot.children) {
                                allFlowers.add(
                                    favorite.getValue(FlowerMain::class.java) ?: FlowerMain()
                                )
                            }
                        }
                        _basketLiveData.postValue(true)
                        Log.e("flowers_splash", "$allFlowers")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }

    fun waitAndGoFather() {
        viewModelScope.launch(Dispatchers.Main) {
            delay(1000)
            _livedata.postValue("getFirebaseFlowerUseCase.execute()")
        }
    }
}