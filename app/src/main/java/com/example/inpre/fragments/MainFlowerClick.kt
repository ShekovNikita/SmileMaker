package com.example.inpre.fragments

import com.example.domain.model.Flower

interface MainFlowerClick {

    fun sendData(flower: Flower)

    fun deleteFlower(flower: Flower)
}