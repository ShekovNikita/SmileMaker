package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.FlowerRepository

class ChangeFlowerInDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flowers: Flower):ArrayList<Flower> {
        return flowerRepository.changeFlowerInData(flowers)
    }
}