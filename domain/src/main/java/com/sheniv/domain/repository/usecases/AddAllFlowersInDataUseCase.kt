package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FlowerRepository

class AddAllFlowersInDataUseCase(private val flowerRepository: FlowerRepository) {

    fun execute(flowers: ArrayList<FlowerMain>) {
        flowerRepository.addAllFlowersInData(flowers)
    }
}