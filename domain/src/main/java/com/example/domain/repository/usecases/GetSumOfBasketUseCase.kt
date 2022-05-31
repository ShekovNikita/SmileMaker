package com.example.domain.repository.usecases

import com.example.domain.repository.FlowerRepository

class GetSumOfBasketUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): Int {
        return flowerRepository.getSumOfBasket()
    }
}