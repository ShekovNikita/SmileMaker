package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FirebaseRepository

class GetFirebaseFlowerUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(): ArrayList<FlowerMain> {
        return firebaseRepository.getFirebaseFlowers() as ArrayList<FlowerMain>
    }
}