package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FlowerRepository

class GetBasketUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): ArrayList<FlowerMain> {
        return flowerRepository.getBasket()
    }
}