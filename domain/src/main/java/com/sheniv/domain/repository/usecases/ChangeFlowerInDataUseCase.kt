package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FlowerRepository

class ChangeFlowerInDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flowers: FlowerMain):ArrayList<FlowerMain> {
        return flowerRepository.changeFlowerInData(flowers)
    }
}