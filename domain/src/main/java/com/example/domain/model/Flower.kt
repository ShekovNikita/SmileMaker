package com.example.domain.model

import java.io.Serializable

data class Flower(
    val name: String,
    val title: String,
    val category: String,
    val info: String,
    val cost: String,
    val image: Int,
    var amount: Int
) : Serializable
