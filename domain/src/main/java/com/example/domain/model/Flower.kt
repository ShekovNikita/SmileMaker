package com.example.domain.model

import java.io.Serializable
import javax.swing.text.html.ImageView

data class Flower(
    val name: String,
    val title: String,
    val category: String,
    val info: String,
    val cost: String,
    val image: Int,
    var basket: Boolean
) : Serializable
