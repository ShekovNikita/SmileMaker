package com.sheniv.inpre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sheniv.inpre.firebase.initFirebase
import com.sheniv.inpre.utilits.showMainActivity
import com.sheniv.inpre.viewmodels.SplashScreenViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashScreen : AppCompatActivity() {

    private val viewModel by viewModel<SplashScreenViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        viewModel.waitAndGoFather()
        subscribeLiveData()
    }

    private fun subscribeLiveData() {
        viewModel.livedata.observe(this) {
            showMainActivity()
            finish()
        }
    }
}