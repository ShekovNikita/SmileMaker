package com.example.data.converters

import com.example.data.storage.models.FirebaseFlower
import com.example.domain.model.Flower
import com.example.domain.repository.Converter

class FirebaseFlowerToFlowerDomainConverter : Converter<FirebaseFlower, Flower> {

    override fun invoke(params: FirebaseFlower): Flower {
        return Flower(
            //params.about ?: "",
            params.articul ?: "",
            params.category ?: "",
            params.cost ?: "",
            params.have ?: "",
            params.hit ?: "",
            0,
            params.name ?: "",
            (params.photos ?: "") as List<String>
        )
    }
}