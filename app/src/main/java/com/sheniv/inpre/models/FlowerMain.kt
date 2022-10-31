package com.sheniv.inpre.models

import java.io.Serializable


data class FlowerMain(
    var articul: String = "",
    var category: String = "",
    var cost: String = "",
    var have: String = "",
    var hit: String = "",
    var name: String = "",
    var amount: Int = 0,
    var photos: List<String> = emptyList()
) : Serializable