package com.sheniv.data.converters

import com.sheniv.data.storage.models.FlowerData
import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.Converter

class ArrayFlowerDomainToArrayFlowerDataConverter :
    Converter<ArrayList<FlowerMain>, ArrayList<FlowerData>> {

    override fun invoke(params: ArrayList<FlowerMain>): ArrayList<FlowerData> {
        val flowerList = mutableListOf<FlowerData>()
        for (i in params) {
            flowerList.add(
                FlowerData(
                    //i.about,
                    i.articul,
                    i.category,
                    i.cost,
                    i.have,
                    i.hit,
                    i.amount,
                    i.name,
                    i.img_source
                )
            )
        }
        return flowerList as ArrayList<FlowerData>
    }
}