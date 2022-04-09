package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class GetBasketUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): ArrayList<Flower> {
        return flowerRepository.getBasket()
    }
}