package com.example.inpre.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.Flower
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentHaveBinding
import com.example.inpre.fragments.ChangeAmountFlowerInBasket
import com.example.inpre.fragments.DeleteFlowerFromBasket
import com.example.inpre.fragments.MainFlowerClick
import com.example.inpre.showActivityAboutFlower
import com.example.inpre.showToast
import com.example.inpre.viewmodels.MainFragmentViewModel
import com.example.inpre.viewmodels.TopFragmentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HaveFragment : BaseFragment<FragmentHaveBinding>(), MainFlowerClick, DeleteFlowerFromBasket,
    ChangeAmountFlowerInBasket {

    private val viewModel by viewModel<TopFragmentsViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHaveBinding.inflate(inflater, container, false)

    override fun FragmentHaveBinding.onBindView(savedInstanceState: Bundle?) {

        viewModel.basketLiveData.observe(viewLifecycleOwner) {
            recyclerFlowersOnMain.adapter =
                MainFlowerAdapter(
                    requireContext(),
                    this@HaveFragment,
                    it as ArrayList<Flower>,
                    this@HaveFragment,
                    this@HaveFragment
                )
        }

        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 2)
    }

    override fun sendData(flower: Flower) {
        activity?.showActivityAboutFlower(flower)
    }

    override fun changeAmountOfFlowerInBasket(flower: Flower) {
        viewModel.changeAmount(flower)
        showToast("Букет добавлен в корзину")
    }

    override fun deleteFlowerFromBasket(flower: Flower) {
        viewModel.deleteFlower(flower)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getHave()
    }
}