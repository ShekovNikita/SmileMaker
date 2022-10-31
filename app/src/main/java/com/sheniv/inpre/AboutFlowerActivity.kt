package com.sheniv.inpre

import android.content.res.Resources
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.sheniv.inpre.adapter.ViewPagerAdapter
import com.sheniv.inpre.databinding.ActivityAboutFlowerBinding
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.showToast
import com.sheniv.inpre.viewmodels.AboutFlowerActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutFlowerActivity : AppCompatActivity() {

    private val viewModel by viewModel<AboutFlowerActivityViewModel>()

    private var _binding: ActivityAboutFlowerBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutFlowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val flower = intent.getSerializableExtra("flower") as FlowerMain

        val size = Resources.getSystem().displayMetrics.widthPixels;
        val params: ViewGroup.LayoutParams = binding.viewPager2.layoutParams
        params.height = size
        binding.viewPager2.layoutParams = params

        binding.viewPager2.adapter = ViewPagerAdapter(flower.photos)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
        }.attach()

        addFlower(flower)
        deleteFlower(flower)

        with(binding) {
            cost.text = "${flower.cost} BYN"
            textFlower.text = flower.name
            articulFlower.text = "Артикул: ${flower.articul}"
        }

        if (viewModel.getBasket().contains(flower)) {
            deleteFlower(flower)
        } else addFlower(flower)

    }

    private fun deleteFlower(flower: FlowerMain) {
        binding.buttonBasket.beGone()
        binding.buttonBasketDelete.beVisible()
        binding.buttonBasketDelete.setOnClickListener {
            viewModel.deleteFlower(flower)
            showToast("Букет удалён из корзины")
            addFlower(flower)
        }
    }

    private fun addFlower(flower: FlowerMain) {
        binding.buttonBasket.beVisible()
        binding.buttonBasketDelete.beGone()
        binding.buttonBasket.setOnClickListener {
            flower.amount += 1
            viewModel.addToBasket(flower)
            showToast("Букет добавлен в корзину")
            deleteFlower(flower)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
