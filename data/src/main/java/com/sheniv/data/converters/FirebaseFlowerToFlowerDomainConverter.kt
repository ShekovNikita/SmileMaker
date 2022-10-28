package com.sheniv.data.converters

import com.sheniv.data.storage.models.FirebaseFlower
import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.Converter

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