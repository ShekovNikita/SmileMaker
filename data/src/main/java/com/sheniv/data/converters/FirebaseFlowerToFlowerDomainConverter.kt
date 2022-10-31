package com.sheniv.data.converters

import com.sheniv.data.storage.models.FirebaseFlower
import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.Converter

class FirebaseFlowerToFlowerDomainConverter : Converter<FirebaseFlower, FlowerMain> {

    override fun invoke(params: FirebaseFlower): FlowerMain {
        return FlowerMain(
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