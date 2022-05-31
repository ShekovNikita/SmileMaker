package com.example.data.converters

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Flower
import com.example.domain.repository.Converter

class FlowerDataToFlowerDomainConverter : Converter<ArrayList<FlowerData>, ArrayList<Flower>> {

    override fun invoke(params: ArrayList<FlowerData>): ArrayList<Flower> {
        val flowerList = mutableListOf<Flower>()
        for (i in params) {
            flowerList.add(
                Flower(
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
        return flowerList as ArrayList<Flower>
    }
}

