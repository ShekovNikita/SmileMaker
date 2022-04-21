package com.example.inpre.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.model.Flower
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


        viewModel.resultLiveData.observe(viewLifecycleOwner){
            summa.text = "Итого: $it"
        }

        btnSendToViber.setOnClickListener {
            navController.navigate(BasketFragmentDirections.actionNavigationBasketToDataAboutBuyer())
        }
    }

    override fun sendData(flower: Flower) {
        activity?.let {
            val intent = Intent(it, AboutFlowerActivity::class.java)
            intent.putExtra("flower", flower)
            it.startActivity(intent)
        }
    }

    override fun deleteFlower(flower: Flower) {
        viewModel.deleteFlower(flower)
    }

    override fun addFlower(flower: Flower) {
        viewModel.changeAmount(flower)
    }
}