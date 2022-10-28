package com.sheniv.domain.repository.usecases

import com.sheniv.domain.model.Flower
import com.sheniv.domain.repository.FirebaseRepository

class GetFirebaseFlowerUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(): ArrayList<Flower> {
        return firebaseRepository.getFirebaseFlowers() as ArrayList<Flower>
    }
}