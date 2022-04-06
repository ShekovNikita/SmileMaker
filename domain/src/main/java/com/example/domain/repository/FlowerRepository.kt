package com.example.domain.repository

import com.example.domain.model.Flower

interface FlowerRepository {

    fun getAllFlower(): List<Flower>

    fun getCategoryOfFlower(): ArrayList<Flower>
}