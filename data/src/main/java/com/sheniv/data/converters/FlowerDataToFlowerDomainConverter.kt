package com.sheniv.data.converters

import com.sheniv.data.storage.models.FlowerData
import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.Converter

class FlowerDataToFlowerDomainConverter : Converter<ArrayList<FlowerData>, ArrayList<FlowerMain>> {

    override fun invoke(params: ArrayList<FlowerData>): ArrayList<FlowerMain> {
        val flowerList = mutableListOf<FlowerMain>()
        for (i in params) {
            flowerList.add(
                FlowerMain(
                   // i.about,
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
        return flowerList as ArrayList<FlowerMain>
    }
}

