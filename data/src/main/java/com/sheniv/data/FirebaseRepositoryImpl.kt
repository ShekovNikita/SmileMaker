package com.sheniv.data

import com.sheniv.data.api.FirebaseApi
import com.sheniv.data.converters.FirebaseFlowerToFlowerDomainConverter
import com.sheniv.domain.model.FlowerMain
import com.sheniv.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(
    private val firebaseApi: FirebaseApi,
    private val firebaseFlowerToFlowerDataConverter: FirebaseFlowerToFlowerDomainConverter
) : FirebaseRepository {

    override suspend fun getFirebaseFlowers(): List<FlowerMain> {
        val flowerList = mutableListOf<FlowerMain>()
        for (i in getFirebaseArticuls()) {
            val response = firebaseApi.getFirebaseFlowers(i)
            if (response.isSuccessful) {
                flowerList.add(firebaseFlowerToFlowerDataConverter.invoke(response.body()!!))
            }
        }
        return flowerList
    }

    suspend fun getFirebaseArticuls(): List<String> {
        val hashMap = mutableMapOf<String, Any>()
        val response = firebaseApi.getFirebaseArticuls()
        if (response.isSuccessful) {
            hashMap.putAll(response.body()!!)
        }
        val s = mutableListOf<String>()
        for (i in hashMap) {
            s.add(i.key)
        }
        return s
    }
}