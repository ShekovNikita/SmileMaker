package com.example.inpre.fragments

import com.example.domain.model.Flower

interface ChangeAmount {

        fun addFlower(flower: Flower)

        fun cutFlower(flower: Flower)
}