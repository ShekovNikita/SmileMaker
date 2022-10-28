package com.sheniv.data.api

import com.sheniv.data.storage.models.FirebaseFlower
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FirebaseApi {

    @GET("all_flowers/flowers/{articul}.json")
    suspend fun getFirebaseFlowers(@Path("articul") articul: String): Response<FirebaseFlower>

    @GET("all_flowers/articuls.json")
    suspend fun getFirebaseArticuls(): Response<HashMap<String, String>>
}