package com.sheniv.domain.repository.usecases

import com.sheniv.domain.repository.FlowerRepository

class GetSumOfBasketUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): Int {
        return flowerRepository.getSumOfBasket()
    }
}