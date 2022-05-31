package com.example.data

import com.example.data.api.FirebaseApi
import com.example.data.converters.FirebaseFlowerToFlowerDomainConverter
import com.example.domain.model.Flower
import com.example.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(
    private val firebaseApi: FirebaseApi,
    private val firebaseFlowerToFlowerDataConverter: FirebaseFlowerToFlowerDomainConverter
) : FirebaseRepository {

    override suspend fun getFirebaseFlowers(): List<Flower> {
        val flowerList = mutableListOf<Flower>()
        for (i in getFirebaseArticuls()) {
            val response = firebaseApi.getFirebaseFlowers(i)
            if (response.isSuccessful) {
                flowerList.add(firebaseFlowerToFlowerDataConverter.invoke(response.body()!!))
            }
        }
        return flowerList
    }

    suspend fun getFirebaseArticuls(): List<String> {
        val hashMap = mutableMapOf<String, String>()
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