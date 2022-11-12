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
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragmentViewModel() : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<FlowerMain>>()
    val basketLiveData: LiveData<ArrayList<FlowerMain>> = _basketLiveData

    init {

    }

    fun getAllFlowers() {
        if (allFlowers.size == 0) {
            viewModelScope.launch(Dispatchers.Main) {
                delay(1000)
                _basketLiveData.postValue(allFlowers)
                Log.e("ALL FLOWERS 00000000", "$allFlowers")
            }
        } else {
            _basketLiveData.postValue(allFlowers)
            Log.e("ALL FLOWERS all", "$allFlowers")
        }
        //Log.e("ALL FLOWERS", "$allFlowers")
    }

    fun sorted() {
        val flowers = mutableListOf<FlowerMain>()
        flowers.addAll(allFlowers as ArrayList<FlowerMain>)
        flowers.sortBy { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>?)
    }

    fun sortedDown() {
        val flowers = mutableListOf<FlowerMain>()
        flowers.addAll(allFlowers)
        flowers.sortByDescending { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>?)
    }
}