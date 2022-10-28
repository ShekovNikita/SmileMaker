package com.sheniv.inpre.fragments

import com.sheniv.domain.model.Flower

interface MainFlowerClick {

    fun sendData(flower: Flower)
}