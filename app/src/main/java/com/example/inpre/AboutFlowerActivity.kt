package com.example.inpre

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.domain.model.Flower
import com.example.inpre.databinding.ActivityAboutFlowerBinding
import com.example.inpre.viewmodel.AboutFlowerActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutFlowerActivity : AppCompatActivity() {

    private val viewModel by viewModel<AboutFlowerActivityViewModel>()

    private var _binding: ActivityAboutFlowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutFlowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val flower = intent.getSerializableExtra("flower") as Flower
        with(binding) {
            println("---------------------------------------addtobasket $flower")
            imageFlower.setImageResource(flower.image)
            cost.text = "${flower.cost} BYN"
            textFlower.text = flower.title
            buttonBasket.setOnClickListener {
                if (flower.amount > 0) {
                    buttonBasket.isClickable = false
                    Toast.makeText(
                        applicationContext,
                        "Букет уже был добавлен добавлен в корзину ",
                        Toast.LENGTH_SHORT).show()
                } else {
                    flower.amount += 1
                    viewModel.addToBasket(flower)
                    Toast.makeText(
                        applicationContext,
                        "Букет добавлен в корзину ",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}