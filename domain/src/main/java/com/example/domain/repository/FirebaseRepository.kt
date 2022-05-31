package com.example.domain.repository

import com.example.domain.model.Flower

interface FirebaseRepository {

    suspend fun getFirebaseFlowers(): List<Flower>
}