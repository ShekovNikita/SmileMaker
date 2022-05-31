package com.example.inpre

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.TypedValue
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.domain.model.Flower
import kotlin.math.roundToInt

fun Activity.showMainActivity() {
    this.startActivity(Intent(this, MainActivity::class.java))
}

fun Activity.showActivityAboutFlower(flower: Flower) {
    this.startActivity(Intent(this, AboutFlowerActivity::class.java).putExtra("flower", flower))
}

fun Activity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun Context.dp2pxFloat(dp: Float): Float =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics)

fun Context.dp2px(dp: Float): Int = dp2pxFloat(dp).roundToInt()