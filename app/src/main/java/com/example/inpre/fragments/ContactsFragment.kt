package com.example.inpre.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentContactsBinding

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding = FragmentContactsBinding.inflate(inflater, container, false)

    override fun FragmentContactsBinding.onBindView(savedInstanceState: Bundle?) {
        val instagram_url = "https://www.instagram.com/smilemaker.by"
        val number = "tel:+375257696633"
        val instagram = Intent(Intent.ACTION_VIEW, Uri.parse(instagram_url))
        val call = Intent(Intent.ACTION_DIAL, Uri.parse(number))

        buttonInstagram.setOnClickListener {
            startActivity(instagram)
        }

        buttonCall.setOnClickListener {
            startActivity(call)
        }
    }
}