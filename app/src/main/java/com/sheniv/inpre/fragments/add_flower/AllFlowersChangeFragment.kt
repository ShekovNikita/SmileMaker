package com.sheniv.inpre.fragments.add_flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.sheniv.inpre.R
import com.sheniv.inpre.firebase.*
import com.sheniv.inpre.adapter.ChangeFlowerAdapter
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentAllFlowersChangeBinding
import com.sheniv.inpre.fragments.ChangeFlowerInFirebase
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import com.sheniv.inpre.utilits.showToast

class AllFlowersChangeFragment : BaseFragment<FragmentAllFlowersChangeBinding>(),
    ChangeFlowerInFirebase {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentAllFlowersChangeBinding.inflate(inflater, container, false)

    override fun FragmentAllFlowersChangeBinding.onBindView(savedInstanceState: Bundle?) {
        recyclerFlowersOnMain.adapter =
            ChangeFlowerAdapter(
                requireContext(),
                allFlowers,
                this@AllFlowersChangeFragment
            )
    }

    override fun deleteFlower(articul: String) {
        REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
            .child(FLOWERS_NODE_CHILD)
            .child(articul)
            .removeValue()
            .addOnCompleteListener { task5 ->
                if (task5.isSuccessful) {
                    val stor = REF_STORAGE_ROOT.child(IMAGE_OF_FLOWERS).child(articul)
                    stor.listAll().addOnSuccessListener { result ->
                        for (item in result.items) {
                            item.delete()
                        }
                    }
                        .addOnCompleteListener {
                            showToast("$articul \nУдалён")
                            println("------------------------------$articul")
                        }

                }
            }
    }

    override fun changeFlower() {
        navController.navigate(R.id.changeCurrentFlowerFragment)
    }

}