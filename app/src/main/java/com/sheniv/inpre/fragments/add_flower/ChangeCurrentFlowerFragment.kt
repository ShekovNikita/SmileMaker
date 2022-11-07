package com.sheniv.inpre.fragments.add_flower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sheniv.inpre.R
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentChangeCurrentFlowerBinding
import com.sheniv.inpre.firebase.*
import com.sheniv.inpre.utilits.*

class ChangeCurrentFlowerFragment : BaseFragment<FragmentChangeCurrentFlowerBinding>() {

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
        bottomNavigationView.beGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
        bottomNavigationView.beVisible()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentChangeCurrentFlowerBinding.inflate(inflater, container, false)

    override fun FragmentChangeCurrentFlowerBinding.onBindView(savedInstanceState: Bundle?) {
        Glide.with(this@ChangeCurrentFlowerFragment)
            .load(changeFlower.photos[0])
            .placeholder(R.drawable.logo_blue)
            .skipMemoryCache(true)
            .into(image)
        articul.text = "Артикул: ${changeFlower.articul}"
        name.setText(changeFlower.name)
        cost.setText(changeFlower.cost)
        flowerHit.isChecked = changeFlower.hit == "hit"

        btnChange.setOnClickListener {
            val dataMap: MutableMap<String, Any> = mutableMapOf()
            dataMap[CHILD_NAME_FLOWER] = name.text.toString()
            dataMap[CHILD_COST_FLOWER] = cost.text.toString()

            var hit = ""
            if (flowerHit.isChecked) {
                hit = "hit"
            }
            dataMap[CHILD_HIT_FLOWER] = hit

            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
                .child(FLOWERS_NODE_CHILD)
                .child(changeFlower.articul)
                .updateChildren(dataMap)
                .addOnCompleteListener { task5 ->
                    if (task5.isSuccessful) {
                        showToast("Изменено")
                        println("------------------------------$dataMap")
                    }
                }
        }
    }
}






