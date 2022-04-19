package com.example.inpre

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.Flower
import com.example.inpre.viewmodel.AboutFlowerActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFlowerActivity : AppCompatActivity() {

    private val viewModel by viewModel<AboutFlowerActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_flower)

        val image_flower = findViewById<ImageView>(R.id.image_flower)
        val cost = findViewById<TextView>(R.id.cost)
        val text_flower = findViewById<TextView>(R.id.text_flower)
        val btn_basket = findViewById<Button>(R.id.button_basket)

        val flower = intent.getSerializableExtra("flower") as Flower
        image_flower.setImageResource(flower.image)
        cost.text = "${flower.cost} BYN"
        text_flower.text = flower.title

        btn_basket.setOnClickListener {
            flower.amount += 1
            viewModel.addToBasket(flower)
            Toast.makeText(applicationContext, "Букет добавлен в корзину ", Toast.LENGTH_SHORT)
                .show()
            btn_basket.isClickable = false
        }
    }

}