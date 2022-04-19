package com.example.data.storage.sourses

import com.example.data.R
import com.example.domain.model.Category

class FlowerCategory {

    private val arrayFlowerCategory = mutableListOf<Category>()

    fun arrayFlowerCategory(): ArrayList<Category> {
        addToArray()
        return arrayFlowerCategory as ArrayList<Category>
    }

    private fun addToArray() {
        arrayFlowerCategory.add(Category("В наличии", R.drawable.rose_1))
        arrayFlowerCategory.add(Category("Хит продаж", R.drawable.rose_2))
        arrayFlowerCategory.add(Category("Розы", R.drawable.rose_3))
        arrayFlowerCategory.add(Category("Пионы", R.drawable.rose_1))
        arrayFlowerCategory.add(Category("Микс", R.drawable.rose_1))
    }
}