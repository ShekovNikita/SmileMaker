package com.example.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.domain.model.Flower
import com.example.inpre.adapter.BasketAdapter
import com.example.inpre.adapter.SwipeToDelete
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentBasketBinding
import com.example.inpre.fragments.ChangeAmount
import com.example.inpre.fragments.DeleteFlower
import com.example.inpre.fragments.MainFlowerClick
import com.example.inpre.showActivityAboutFlower
import com.example.inpre.viewmodels.BasketFragmentViewModel
import com.example.inpre.viewmodels.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BasketFragment : BaseFragment<FragmentBasketBinding>(), MainFlowerClick, ChangeAmount,
    DeleteFlower {

    private val viewModel by viewModel<BasketFragmentViewModel>()
    private val viewModelMain by viewModel<MainFragmentViewModel>()

    private val mainFlowerAdapter by lazy {
        BasketAdapter(
            requireContext(),
            this,
            viewModel.getBasket(),
            this,
            this
        )
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false)

    override fun FragmentBasketBinding.onBindView(savedInstanceState: Bundle?) {

        initRecyclerView()

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it > 0) {
                summa.text = "Итого: $it"
                btnSendToViber.visibility = View.VISIBLE
            } else {
                summa.text = "Корзина пуста"
                btnSendToViber.visibility = View.GONE
            }
        }

        viewModel.basketLiveData.observe(viewLifecycleOwner){
            recyclerBasket.adapter = BasketAdapter(
                requireContext(),
                this@BasketFragment,
                it as ArrayList<Flower>,
                this@BasketFragment,
                this@BasketFragment
            )
        }

        btnSendToViber.setOnClickListener {
            navController.navigate(BasketFragmentDirections.actionNavigationBasketToDataAboutBuyer())
        }
    }

    private fun initRecyclerView() {
        binding.recyclerBasket.adapter = mainFlowerAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(mainFlowerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerBasket)
    }

    override fun sendData(flower: Flower) {
        activity?.showActivityAboutFlower(flower)
    }

    override fun deleteFlower(flower: Flower) {
        viewModel.deleteFlower(flower)
    }

    override fun addFlower(flower: Flower) {
        viewModel.changeAmount(flower)
    }
}