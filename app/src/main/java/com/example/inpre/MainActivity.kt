package com.example.inpre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.inpre.adapter.CategoryAdapter
import com.example.inpre.databinding.ActivityMainBinding
import com.example.inpre.viewmodel.MainActivityViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModelMain by viewModel<MainActivityViewModel>()

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.recyclerCategory.adapter = categoryAdapter
        categoryAdapter.addCategory(viewModelMain.getCategory())

        val navView: BottomNavigationView = findViewById(R.id.navigationView)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController( navController, viewModelMain.getAppBarConfiguration())
        setupWithNavController(navView,navController)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}