package com.example.data.storage

import android.content.Context
import com.example.inpre.R
import com.example.data.storage.models.FlowerData
import com.example.data.storage.sourses.AllFlowers


class AppDrawableFlowerStorage(context: Context) : FlowerStorage {

    val arrayAboutFlower = context.resources.getStringArray(R.array.flower_name_cost_title)

    override fun getAllFlower(): ArrayList<FlowerData> {

        var flowerList = mutableListOf<FlowerData>()
        var fl = 0
        val flowers = AllFlowers().arrayFlower()

        for (i in arrayAboutFlower) {
            val flower = Regex("[/]").split(i.toString())

            flowerList.add(
                FlowerData(
                    name = flower[0],
                    title = flower[1],
                    category = flower[2],
                    info = flower[3],
                    cost = flower[4],
                    image = flowers[fl++]
                )
            )
            println("=============================================$flowerList")
        }
        return flowerList as ArrayList<FlowerData>
    }

    override fun getCategoryOfFlower(): ArrayList<FlowerData> {
        for (i in getAllFlower()) {

        }
        return arrayListOf()
    }

    fun getFlower() {
    }
}
