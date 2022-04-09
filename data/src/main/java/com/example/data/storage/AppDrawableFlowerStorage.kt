package com.example.data.storage

import android.content.Context
import com.example.data.R
import com.example.data.storage.models.FlowerData
import com.example.data.storage.sourses.AllFlowers
import com.example.data.storage.sourses.Basket
import com.example.data.storage.sourses.FlowerCategory
import com.example.domain.model.Category
import com.example.domain.model.Flower


class AppDrawableFlowerStorage(context: Context) : FlowerStorage {

    private val arrayAboutFlower = context.resources.getStringArray(R.array.flower_name_cost_title)

    val b = Basket()

    override fun getAllFlower(): ArrayList<FlowerData> {

        val flowerList = mutableListOf<FlowerData>()
        val flowers = AllFlowers().arrayFlower()

        for ((fl, i) in arrayAboutFlower.withIndex()) {
            val flower = Regex("[/]").split(i.toString())

            flowerList.add(
                FlowerData(
                    name = flower[0],
                    title = flower[1],
                    category = flower[2],
                    info = flower[3],
                    cost = flower[4],
                    image = flowers[fl],
                    basket = false
                )
            )
        }
        return flowerList as ArrayList<FlowerData>
    }

    override fun getCategoryOfFlower(): ArrayList<Category> {
        return FlowerCategory().arrayFlowerCategory()
    }

    override fun addToBasket(flowerData: FlowerData) {
        b.addToBasket(flowerData)
    }

    override fun getBasket(): ArrayList<Flower> {
        val basket = b.getBasket()
        val arrayFlower = arrayListOf<Flower>()
        for (i in basket){
            arrayFlower.add(
                Flower(
                    i.name,
                    i.title,
                    i.category,
                    i.info,
                    i.cost,
                    i.image,
                    i.basket
            )
            )
        }
        return arrayFlower
    }
}
