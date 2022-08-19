package com.example.data.converters

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Flower
import com.example.domain.repository.Converter

class FlowerDomainToFlowerDataConverter : Converter<Flower, FlowerData> {

    override fun invoke(i: Flower): FlowerData {
        return FlowerData(
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
    }
}
