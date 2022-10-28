package com.sheniv.inpre

import android.content.res.Resources
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.sheniv.domain.model.Flower
import com.sheniv.inpre.adapter.ViewPagerAdapter
import com.sheniv.inpre.databinding.ActivityAboutFlowerBinding
import com.sheniv.inpre.viewmodels.AboutFlowerActivityViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.sheniv.inpre.utilits.showToast
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
            for (i in viewModel.getBasket()) {
                if (i.articul == flower.articul) {
                    buttonBasket.text = "Удалить из корзины"
                    buttonBasket.setBackgroundColor(Color.parseColor("#CC0000"))
                }
            }

            val size = Resources.getSystem().displayMetrics.widthPixels;
            val params: ViewGroup.LayoutParams = viewPager2.layoutParams
            params.height = size
            viewPager2.layoutParams = params

            viewPager2.adapter = ViewPagerAdapter(flower.img_source)

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            }.attach()

            cost.text = "${flower.cost} BYN"
            //about.text = flower.about
            textFlower.text = flower.name
            articulFlower.text = "Артикул: ${flower.articul}"
            buttonBasket.setOnClickListener {
                buttonBasket.visibility = View.INVISIBLE
                flower.amount += 1
                viewModel.addToBasket(flower)
                showToast("Букет добавлен в корзину")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}