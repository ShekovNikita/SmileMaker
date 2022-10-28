package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.Category
import com.sheniv.domain.repository.FlowerRepository

class GetCategoryFlowersUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): ArrayList<Category> {
        return flowerRepository.getCategoryOfFlower()
    }
}