package com.example.data.converters

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Flower
import com.example.domain.repository.Converter

class ArrayFlowerDomainToArrayFlowerDataConverter :
    Converter<ArrayList<Flower>, ArrayList<FlowerData>> {

    override fun invoke(params: ArrayList<Flower>): ArrayList<FlowerData> {
        val flowerList = mutableListOf<FlowerData>()
        for (i in params) {
            flowerList.add(
                FlowerData(
                    i.about,
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