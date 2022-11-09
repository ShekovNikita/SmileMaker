package com.sheniv.inpre.utilits

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sheniv.inpre.AboutFlowerActivity
import com.sheniv.inpre.MainActivity
import com.sheniv.inpre.basket.Basket
import com.sheniv.inpre.models.FlowerMain
import java.text.SimpleDateFormat
import java.util.*

lateinit var recyclerTop: RecyclerView
var allFlowers = arrayListOf<FlowerMain>()
var basket = Basket()
lateinit var bottomNavigationView: BottomNavigationView
lateinit var APP_ACTIVITY: MainActivity
lateinit var changeFlower: FlowerMain

fun Activity.showMainActivity() {
    this.startActivity(Intent(this, MainActivity::class.java))
}

fun View.beGone(){
    this.visibility = View.GONE
}

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun activityAboutFlower(flower: FlowerMain){
    APP_ACTIVITY.startActivity(Intent(
        APP_ACTIVITY,
        AboutFlowerActivity::class.java
    ).putExtra("flower", flower))
}

fun fragmentAboutFlower(){}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun String.asTime(): String {
    val time = Date(this.toLong())
    val timeFormat = SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault())
    return timeFormat.format(time)
}