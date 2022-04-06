package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class GetAllFlowersUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): List<Flower> {
        return flowerRepository.getAllFlower()
    }
}