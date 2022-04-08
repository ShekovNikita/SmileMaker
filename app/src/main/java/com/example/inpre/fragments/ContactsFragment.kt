package com.example.inpre.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Transformations.map
import com.example.inpre.R
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentContactsBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ContactsFragment : BaseFragment<FragmentContactsBinding>() {
    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding = FragmentContactsBinding.inflate(inflater, container, false)

    override fun FragmentContactsBinding.onBindView(savedInstanceState: Bundle?) {
    }
}