package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class AddAllFlowersInDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flowers: ArrayList<Flower>) {
        flowerRepository.addAllFlowersInData(flowers)
    }
}