package com.sheniv.inpre

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.sheniv.data.firebase.initFirebase
import com.sheniv.inpre.adapter.CategoryAdapter
import com.sheniv.inpre.databinding.ActivityMainBinding
import com.sheniv.inpre.fragments.CategoryClick
import com.sheniv.inpre.viewmodels.BasketFragmentViewModel
import com.sheniv.inpre.viewmodels.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.recyclerTop
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), CategoryClick {

    private val viewModelMain by viewModel<MainActivityViewModel>()
    private val viewModel by viewModel<BasketFragmentViewModel>()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val categoryAdapter = CategoryAdapter(this, this)


    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()

        supportActionBar?.hide()

        recyclerTop = binding.recyclerCategory
        recyclerTop.adapter = categoryAdapter
        recyclerTop.beGone()
        categoryAdapter.addCategory(viewModelMain.getCategory())

        control()
        viewModel.getBasket()

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun choiceCategory(category: String) {
        val nav = findNavController(R.id.fragment)
        nav.popBackStack()
        when (category) {
            "Розы" -> nav.navigate(R.id.roseFragment)
            "Хиты" -> nav.navigate(R.id.hitFragment)
            "В наличии" -> nav.navigate(R.id.haveFragment)
            "Пионы" -> nav.navigate(R.id.pioniFragment)
            "Микс" -> nav.navigate(R.id.mixFragment)
        }
    }

    private fun control() {
        val navView: BottomNavigationView = findViewById(R.id.navigationView)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController, viewModelMain.getAppBarConfiguration())
        setupWithNavController(navView, navController)

        var badge = navView.getOrCreateBadge(R.id.navigation_basket)
        viewModel.basketLiveData.observe(this) {
            if (it.size.toString() == "0"){
                badge.isVisible = false
                viewModel.getBasket()
            } else {
                badge.number = it.size
                badge.isVisible = true
                viewModel.getBasket()
            }
        }
    }
}