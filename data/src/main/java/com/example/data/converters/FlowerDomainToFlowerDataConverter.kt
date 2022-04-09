package com.example.data.converters

import com.example.data.storage.models.FlowerData
import com.example.domain.model.Flower
import com.example.domain.repository.Converter

class FlowerDomainToFlowerDataConverter: Converter<Flower, FlowerData> {
    override fun invoke(i: Flower): FlowerData{
        return  FlowerData(
                    i.name,
                    i.title,
                    i.category,
                    i.info,
                    i.cost,
                    i.image,
                    i.basket
                )
    }
}