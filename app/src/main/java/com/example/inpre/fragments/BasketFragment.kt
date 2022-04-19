package com.example.inpre.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.model.Flower
import com.example.domain.repository.FlowerRepository
import com.example.domain.repository.usecases.ChangeAmountOfOneFlowerUseCase
import com.example.inpre.AboutFlowerActivity
import com.example.inpre.adapter.BasketAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentBasketBinding
import com.example.inpre.viewmodel.BasketFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BasketFragment : BaseFragment<FragmentBasketBinding>(), MainFlowerClick, ChangeAmount {

    private val viewModel by viewModel<BasketFragmentViewModel>()

    private val mainFlowerAdapter by lazy { BasketAdapter(this, viewModel.getBasket(), this) }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false)

    override fun FragmentBasketBinding.onBindView(savedInstanceState: Bundle?) {

        recyclerBasket.adapter = mainFlowerAdapter
        summa.text = "Итого: ${viewModel.getSummaInBasket()} BYN"

        btnSendToViber.setOnClickListener {
            val sb = StringBuffer()
            for (i in viewModel.getBasket()){
                sb.append("Название: ${i.title}\nКоличество: \n\n")
            }
            sb.append("\nИтого:${viewModel.getSummaInBasket()}")
            composeEmail(sb.toString())
        }
    }

    override fun sendData(flower: Flower) {
        activity?.let {
            val intent = Intent(it, AboutFlowerActivity::class.java)
            intent.putExtra("flower", flower)
            it.startActivity(intent)
        }
    }

    fun composeEmail(message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf("shekovnikita8@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "theme")
            putExtra(Intent.EXTRA_TEXT, message)
        }
        startActivity(intent)

    }

    override fun addFlower(flower: Flower) {
        viewModel.changeAmount(flower)
    }

    override fun cutFlower(flower: Flower) {
        viewModel.changeAmount(flower)
    }
}