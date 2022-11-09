package com.sheniv.inpre.fragments.bottom

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.sheniv.inpre.R
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentContactsBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding = FragmentContactsBinding.inflate(inflater, container, false)

    override fun FragmentContactsBinding.onBindView(savedInstanceState: Bundle?) {

        val instagram_url = "https://www.instagram.com/smilemaker.by"
        val number = "tel:+375257696633"
        val web_site = "https://www.smilemaker.by"
        //val slivki = "https://gomel.slivki.by/mylnye-tsvety-skidka-smilemaker?utm_source=search_result&utm_medium=smile%20maker"
        val instagram = Intent(Intent.ACTION_VIEW, Uri.parse(instagram_url))
        val call = Intent(Intent.ACTION_DIAL, Uri.parse(number))
        val web = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(web_site)))
        //val slivkiby = Intent(Intent(Intent.ACTION_VIEW, Uri.parse(slivki)))

        buttonInstagram.setOnClickListener {
            startActivity(instagram)
        }

        buttonCall.setOnClickListener {
            startActivity(call)
        }

        buttonWeb.setOnClickListener {
            startActivity(web)
        }

        btnBonus.setOnClickListener { navController.navigate(R.id.navigation_bonus) }

        btnDelivery.setOnClickListener { navController.navigate(R.id.navigation_delivery) }

        btnAboutCare.setOnClickListener { navController.navigate(R.id.aboutCareFragment) }

        /*buttonSlivki.setOnClickListener {
            startActivity(slivkiby)
        }*/
    }
}