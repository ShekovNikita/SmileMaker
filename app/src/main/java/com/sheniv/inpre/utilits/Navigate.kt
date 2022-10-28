package com.sheniv.inpre.utilits

import android.app.Activity
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.sheniv.domain.model.Flower
import com.sheniv.inpre.AboutFlowerActivity
import com.sheniv.inpre.MainActivity

lateinit var recyclerTop: RecyclerView

fun Activity.showMainActivity() {
    this.startActivity(Intent(this, MainActivity::class.java))
}

fun View.beGone(){
    this.visibility = View.GONE
}

fun View.beVisible(){
    this.visibility = View.VISIBLE
}

fun Activity.showActivityAboutFlower(flower: Flower) {
    this.startActivity(
        Intent(
            this,
            AboutFlowerActivity::class.java
        ).putExtra("flower", flower)
    )
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}