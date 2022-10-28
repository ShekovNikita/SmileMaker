package com.sheniv.domain.repository

import com.sheniv.domain.model.Flower

interface FirebaseRepository {

    suspend fun getFirebaseFlowers(): List<Flower>
}