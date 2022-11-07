package com.sheniv.inpre.fragments.bottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentInfoBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.utilits.showToast

class InfoFragment : BaseFragment<FragmentInfoBinding>() {

    private var interAd: InterstitialAd? = null

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
        binding.adView.resume()
        loadInterAd()
    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
        //binding.adView.destroy()
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentInfoBinding.inflate(inflater, container, false)

    override fun FragmentInfoBinding.onBindView(savedInstanceState: Bundle?) {
        initAdMob()
        btnAds.setOnClickListener {
            showInterAd()
        }
    }

    private fun initAdMob() {
        MobileAds.initialize(requireActivity())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    private fun loadInterAd(){
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(requireActivity(),
            "ca-app-pub-3940256099942544/1033173712",
        adRequest,
        object : InterstitialAdLoadCallback(){
            override fun onAdFailedToLoad(p0: LoadAdError) {
                interAd = null
            }

            override fun onAdLoaded(p0: InterstitialAd) {
                interAd = p0
            }
        })
    }

    private fun showInterAd(){
        if (interAd != null){
            interAd?.fullScreenContentCallback = object : FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    showContent()
                    interAd = null
                    loadInterAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    showContent()
                    interAd = null
                    loadInterAd()
                }

                override fun onAdShowedFullScreenContent() {
                    showContent()
                    interAd = null
                    loadInterAd()
                }
            }
            interAd?.show(requireActivity())
        } else {
            showContent()
        }
    }

    private fun showContent(){
        showToast("Спасибо за просмотр рекламы")
    }
}