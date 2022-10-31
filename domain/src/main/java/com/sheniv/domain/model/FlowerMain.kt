package com.sheniv.domain.model

import java.io.Serializable

data class FlowerMain(
    //val about: String,
    val articul: String,
    val category: String,
    val cost: String,
    val have: String,
    val hit: String,
    var amount: Int,
    val name: String,
    val img_source: List<String>
) : Serializable
