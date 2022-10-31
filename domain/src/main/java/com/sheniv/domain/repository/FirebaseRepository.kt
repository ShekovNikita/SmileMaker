package com.sheniv.domain.repository

import com.sheniv.domain.model.FlowerMain

interface FirebaseRepository {

    suspend fun getFirebaseFlowers(): List<FlowerMain>
}