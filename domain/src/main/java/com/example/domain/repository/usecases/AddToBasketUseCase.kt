package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository

class AddToBasketUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flower: Flower){
        flowerRepository.addToBasket(flower)
    }
}