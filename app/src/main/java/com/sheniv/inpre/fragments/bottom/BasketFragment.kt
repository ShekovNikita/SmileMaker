package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import com.sheniv.domain.model.Flower
import com.sheniv.inpre.adapter.BasketAdapter
import com.sheniv.inpre.adapter.SwipeToDelete
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentBasketBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.fragments.MainFlowerClick
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.utilits.showActivityAboutFlower
import com.sheniv.inpre.viewmodels.BasketFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BasketFragment : BaseFragment<FragmentBasketBinding>(), MainFlowerClick, ChangeAmountFlowerInBasket,
    DeleteFlowerFromBasket {

    private val viewModel by viewModel<BasketFragmentViewModel>()

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
        recyclerTop.beGone()

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

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
    }

    private fun initRecyclerView() {
        binding.recyclerBasket.adapter = mainFlowerAdapter

        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(mainFlowerAdapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerBasket)
    }

    override fun sendData(flower: Flower) {
        activity?.showActivityAboutFlower(flower)
    }

    override fun deleteFlowerFromBasket(flower: Flower) {
        viewModel.deleteFlower(flower)
    }

    override fun changeAmountOfFlowerInBasket(flower: Flower) {
        viewModel.changeAmount(flower)
    }
}