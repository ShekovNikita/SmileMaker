package com.example.data.storage.models

import com.google.gson.annotations.SerializedName

data class FirebaseFlower(
    @SerializedName("about") var about: String? = null,
    @SerializedName("articul") var articul: String? = null,
    @SerializedName("category") var category: String? = null,
    @SerializedName("cost") var cost: String? = null,
    @SerializedName("have") var have: String? = null,
    @SerializedName("hit") var hit: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("photos") var photos: List<String>? = null
)
