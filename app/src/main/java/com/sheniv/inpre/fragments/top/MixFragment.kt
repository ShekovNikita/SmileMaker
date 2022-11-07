package com.sheniv.inpre.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sheniv.inpre.adapter.MainFlowerAdapter
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentMixBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.showToast
import com.sheniv.inpre.viewmodels.TopFragmentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MixFragment : BaseFragment<FragmentMixBinding>(),
    ChangeAmountFlowerInBasket {

    private val viewModel by viewModel<TopFragmentsViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentMixBinding.inflate(inflater, container, false)

    override fun FragmentMixBinding.onBindView(savedInstanceState: Bundle?) {

        viewModel.basketLiveData.observe(viewLifecycleOwner) {
            recyclerFlowersOnMain.adapter =
                MainFlowerAdapter(
                    requireContext(),
                    it as ArrayList<FlowerMain>,
                )
        }

        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 2)
    }

    override fun changeAmountFlowerInBasket() {
        showToast("Букет добавлен в корзину")
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMix()
    }
}