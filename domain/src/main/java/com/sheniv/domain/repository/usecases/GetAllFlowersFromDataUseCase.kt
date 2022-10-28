package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.FlowerRepository

class GetAllFlowersFromDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): ArrayList<Flower> {
        return flowerRepository.getAllFlowersFromData()
    }
}