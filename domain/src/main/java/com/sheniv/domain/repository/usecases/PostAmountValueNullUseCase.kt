package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.FlowerRepository

class PostAmountValueNullUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flower: Flower) {
        flowerRepository.postAmountValueNull(flower)
    }
}