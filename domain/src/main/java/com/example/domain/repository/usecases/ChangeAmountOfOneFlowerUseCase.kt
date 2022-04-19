package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class ChangeAmountOfOneFlowerUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flower: Flower): ArrayList<Flower>{
        return flowerRepository.changeAmountOfOneFlower(flower)
    }
}