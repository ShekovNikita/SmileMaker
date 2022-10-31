package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FlowerRepository

class PostAmountValueNullUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flower: FlowerMain) {
        flowerRepository.postAmountValueNull(flower)
    }
}