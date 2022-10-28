package com.sheniv.data.storage.sourses

import com.sheniv.domain.model.Category

class FlowerCategory {

    private val arrayFlowerCategory = mutableListOf<Category>()

    fun arrayFlowerCategory(): ArrayList<Category> {
        addToArray()
        return arrayFlowerCategory as ArrayList<Category>
    }

    private fun addToArray() {
        arrayFlowerCategory.add(
            Category(
                "В наличии",
                "https://smilemaker.by/wp-content/uploads/2022/01/photo_2022-01-17_19-13-52.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "Хиты",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-38-17-600x600.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "Розы",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-50-00.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "Пионы",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_15-15-29-600x600.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "Микс",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-51-01.jpg"
            )
        )
    }
}