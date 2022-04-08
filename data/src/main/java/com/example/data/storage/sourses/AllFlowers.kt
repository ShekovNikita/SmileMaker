package com.example.data.storage.sourses

import com.example.data.R


class AllFlowers {

    private val arrayFlower = mutableListOf<Int>()

    fun arrayFlower(): List<Int> {
        addToArray()
        return arrayFlower
    }

    private fun addToArray() {
        /*arrayFlower.add("https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-37-52.jpg")
        arrayFlower.add("https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-37-52.jpg")
        arrayFlower.add("https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-37-52.jpg")
        arrayFlower.add("https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-37-52.jpg")*/
        arrayFlower.add(R.drawable.rose_2)
        arrayFlower.add(R.drawable.rose_3)
        arrayFlower.add(R.drawable.rose_3)
        arrayFlower.add(R.drawable.igles_blue)
    }
}