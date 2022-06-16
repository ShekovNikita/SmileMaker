package com.example.inpre.fragments.top

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.Flower
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentHaveBinding
import com.example.inpre.fragments.ChangeAmount
import com.example.inpre.fragments.DeleteFlower
import com.example.inpre.fragments.MainFlowerClick
import com.example.inpre.showActivityAboutFlower
import com.example.inpre.viewmodels.TopFragmentsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HaveFragment : BaseFragment<FragmentHaveBinding>(), MainFlowerClick, DeleteFlower,
    ChangeAmount {

    private val viewModel by viewModel<TopFragmentsViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentHaveBinding.inflate(inflater, container, false)

    override fun FragmentHaveBinding.onBindView(savedInstanceState: Bundle?) {

        viewModel.getHave()

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

    override fun addFlower(flower: Flower) {
        TODO("Not yet implemented")
    }

    override fun deleteFlower(flower: Flower) {
        TODO("Not yet implemented")
    }
}