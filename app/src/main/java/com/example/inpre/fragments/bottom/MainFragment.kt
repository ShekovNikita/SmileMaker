package com.example.inpre.fragments.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.domain.model.Flower
import com.example.inpre.adapter.MainFlowerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentMainBinding
import com.example.inpre.fragments.MainFlowerClick
import com.example.inpre.showActivityAboutFlower
import com.example.inpre.viewmodels.MainFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : BaseFragment<FragmentMainBinding>(),
    MainFlowerClick {

    private val viewModelMain by viewModel<MainFragmentViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding =
        FragmentMainBinding.inflate(inflater, container, false)


    override fun FragmentMainBinding.onBindView(savedInstanceState: Bundle?) {

        viewModelMain.basketLiveData.observe(viewLifecycleOwner) {
            recyclerFlowersOnMain.adapter =
                MainFlowerAdapter(requireContext(), this@MainFragment, it as ArrayList<Flower>)
        }

        recyclerFlowersOnMain.layoutManager = GridLayoutManager(context, 2)

        addFlower.setOnClickListener {
            navController.navigate(MainFragmentDirections.actionNavigationMainToRegisterFragment())
        }
    }

    override fun sendData(flower: Flower) {
        activity?.showActivityAboutFlower(flower)
    }
}