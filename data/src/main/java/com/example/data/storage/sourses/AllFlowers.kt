package com.example.data.storage.sourses

import com.example.data.R


class AllFlowers {

    private val arrayFlower = mutableListOf<Int>()

    fun arrayFlower(): List<Int> {
        addToArray()
        return arrayFlower
    }

    private fun addToArray() {
        arrayFlower.add(R.drawable.rose_1)
        arrayFlower.add(R.drawable.rose_2)
        arrayFlower.add(R.drawable.rose_3)
        arrayFlower.add(R.drawable.igles_blue)
    }
}