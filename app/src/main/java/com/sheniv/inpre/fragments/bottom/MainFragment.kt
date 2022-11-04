package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sheniv.inpre.adapter.MainFlowerAdapter
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentMainBinding
import com.sheniv.inpre.firebase.*
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.models.User
import com.sheniv.inpre.utilits.*
import com.sheniv.inpre.viewmodels.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(), DeleteFlowerFromBasket, ChangeAmountFlowerInBasket {

    private val viewModelMain by viewModel<MainFragmentViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)


    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {

        viewModelMain.basketLiveData.observe(viewLifecycleOwner) {
            progressBar.visibility = View.VISIBLE
            if (it.size != 0) {
                progressBar.beGone()
                recyclerTop.beVisible()
                recyclerFlowersOnMain.adapter =
                    MainFlowerAdapter(
                        requireContext(),
                        it,
                        this@MainFragment,
                        this@MainFragment
                    )
            }
        }

        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 2)

        sorted.setOnClickListener {
            viewModelMain.sorted()
            viewModelMain.basketLiveData.observe(viewLifecycleOwner) {
                recyclerFlowersOnMain.adapter =
                    MainFlowerAdapter(
                        requireContext(),
                        it as ArrayList<FlowerMain>,
                        this@MainFragment,
                        this@MainFragment
                    )
            }
        }

        sortedDown.setOnClickListener {
            viewModelMain.sortedDown()
            viewModelMain.basketLiveData.observe(viewLifecycleOwner) {
                recyclerFlowersOnMain.adapter =
                    MainFlowerAdapter(
                        requireContext(),
                        it as ArrayList<FlowerMain>,
                        this@MainFragment,
                        this@MainFragment
                    )
            }
        }
    }



    override fun changeAmountFlowerInBasket() {
        //viewModelMain.changeAmount()
        showToast("Букет добавлен в корзину")
    }

    override fun deleteFlowerFromBasket(flower: FlowerMain) {
        basket.deleteFromBasket(flower)
    }

    override fun onResume() {
        super.onResume()
        viewModelMain.getAllFlowers()
    }
}