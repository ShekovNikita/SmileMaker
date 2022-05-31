package com.example.domain.repository.usecases

import com.example.domain.model.Flower
import com.example.domain.repository.FirebaseRepository

class GetFirebaseFlowerUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(): ArrayList<Flower> {
        return firebaseRepository.getFirebaseFlowers() as ArrayList<Flower>
    }
}