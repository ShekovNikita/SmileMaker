package com.sheniv.inpre.fragments

import com.sheniv.inpre.fragments.bottom.MainFragment
import com.sheniv.inpre.models.FlowerMain

interface ChangeFlowerInFirebase {

    fun deleteFlower(articul: String)

    fun changeFlower()
}