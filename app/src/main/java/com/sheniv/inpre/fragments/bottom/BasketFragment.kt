package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.snackbar.Snackbar
import com.sheniv.inpre.adapter.BasketAdapter
import com.sheniv.inpre.adapter.SwipeToDelete
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentBasketBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.basket
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.viewmodels.BasketFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class BasketFragment : BaseFragment<FragmentBasketBinding>(), ChangeAmountFlowerInBasket {

    private val viewModel by viewModel<BasketFragmentViewModel>()

    private val simpleAdapter by lazy { BasketAdapter(requireContext(), this) }

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
        updateList()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBasketBinding = FragmentBasketBinding.inflate(inflater, container, false)

    override fun FragmentBasketBinding.onBindView(savedInstanceState: Bundle?) {

        initRecyclerView()

        recyclerBasket.adapter = simpleAdapter

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            if (it > 0) {
                summa.text = "Итого: $it"
                btnSendToViber.beVisible()
                summa.beVisible()
                imageEmpty.beGone()
                textEmpty.beGone()
            } else {
                summa.beGone()
                btnSendToViber.beGone()
                imageEmpty.beVisible()
                textEmpty.beVisible()
            }
        }

        btnSendToViber.setOnClickListener {
            navController.navigate(BasketFragmentDirections.actionNavigationBasketToDataAboutBuyer())
        }
    }

    private fun updateList(){
        simpleAdapter.differ.submitList(viewModel.getBasket())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
    }

    override fun changeAmountFlowerInBasket() {
        updateList()
    }

    private fun initRecyclerView() {
        val itemTouchHelper = ItemTouchHelper(SwipeToDelete(simpleAdapter))
        itemTouchHelper.attachToRecyclerView(binding.recyclerBasket)
    }
}