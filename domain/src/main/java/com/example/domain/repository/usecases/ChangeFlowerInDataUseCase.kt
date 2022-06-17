package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class ChangeFlowerInDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flowers: Flower):ArrayList<Flower> {
        return flowerRepository.changeFlowerInData(flowers)
    }
}