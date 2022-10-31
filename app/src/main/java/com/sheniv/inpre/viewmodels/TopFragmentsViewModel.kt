package com.sheniv.inpre.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sheniv.domain.repository.usecases.ChangeFlowerInDataUseCase
import com.sheniv.domain.repository.usecases.PostAmountValueNullUseCase
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import com.sheniv.inpre.utilits.basket

class TopFragmentsViewModel(
    private val postAmountValueNullUseCase: PostAmountValueNullUseCase,
    private val changeFlowerInDataUseCase: ChangeFlowerInDataUseCase
) : ViewModel() {

    private var _basketLiveData = MutableLiveData<ArrayList<FlowerMain>>()
    val basketLiveData: LiveData<ArrayList<FlowerMain>> = _basketLiveData

    fun getRoses() {
        val flowers = mutableListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.category == "розы")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>)
    }

    fun getHit() {
        val flowers = mutableListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.hit == "hit")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>)
    }

    fun getHave() {
        val flowers = mutableListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.have == "have")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>)
    }

    fun getPioni() {
        val flowers = mutableListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.category == "пионы")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>)
    }

    fun getMix() {
        val flowers = mutableListOf<FlowerMain>()
        for (i in allFlowers){
            if (i.category == "микс")
                flowers.add(i)
        }
        _basketLiveData.postValue(flowers as ArrayList<FlowerMain>)
    }

    fun deleteFlower(flower: FlowerMain) {
        basket.deleteFromBasket(flower)
    }

    /*fun changeAmount(flower: com.sheniv.domain.model.FlowerMain): ArrayList<com.sheniv.domain.model.FlowerMain> {
        return changeFlowerInDataUseCase.execute(flower)
    }*/
}