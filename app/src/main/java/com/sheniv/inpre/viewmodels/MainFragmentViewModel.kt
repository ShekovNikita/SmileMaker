package com.sheniv.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sheniv.data.firebase.ALL_FLOWERS_NODE
import com.sheniv.data.firebase.FLOWERS_NODE_CHILD
import com.sheniv.data.firebase.REF_DATABASE_ROOT
import com.sheniv.domain.repository.usecases.*
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getAllFlowersFromDataUseCase: GetAllFlowersFromDataUseCase,
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase,
    private val getFirebaseFlowerUseCase: GetFirebaseFlowerUseCase,
    private val addAllFlowersInDataUseCase: AddAllFlowersInDataUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<FlowerMain>>()
    val basketLiveData: LiveData<ArrayList<FlowerMain>> = _basketLiveData

    init {
        viewModelScope.launch(Dispatchers.Main) {
            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE).child(FLOWERS_NODE_CHILD)
                .addValueEventListener(object : ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        allFlowers.clear()
                        if (snapshot.exists()) {
                            for (favorite in snapshot.children) {
                                allFlowers.add(
                                    favorite.getValue(FlowerMain::class.java) ?: FlowerMain()
                                )
                            }
                        }
                        _basketLiveData.postValue(allFlowers)
                        Log.e("flowers", "$allFlowers")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }
    }

    fun getAllFlowers(){
        _basketLiveData.postValue(allFlowers as ArrayList<FlowerMain>)
    }

    fun sorted(){
        val flowers = mutableListOf<FlowerMain>()
        flowers.addAll(allFlowers as ArrayList<FlowerMain>)
        flowers.sortBy { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>?)
    }

    fun sortedDown(){
        val flowers = mutableListOf<FlowerMain>()
        flowers.addAll(allFlowers)
        flowers.sortByDescending { it.cost.toInt() }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>?)
    }

    fun deleteFlower(flower: com.sheniv.domain.model.FlowerMain) {
        postAmountValueNullUseCase.execute(flower)
    }

    /*fun changeAmount(): ArrayList<com.sheniv.domain.model.FlowerMain> {
        return changeFlowerInDataUseCase.execute()
    }*/
}