package com.sheniv.data.converters

import com.sheniv.data.storage.models.FlowerData
import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.Converter

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
