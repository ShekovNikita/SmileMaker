package com.example.domain.repository.usecases

import com.example.domain.model.Category
import com.example.domain.repository.FlowerRepository

class GetCategoryFlowersUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(): ArrayList<Category> {
        return flowerRepository.getCategoryOfFlower()
    }
}