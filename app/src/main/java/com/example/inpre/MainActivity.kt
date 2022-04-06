package com.example.inpre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.inpre.adapter.CategoryAdapter
import com.example.inpre.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModelMain by viewModel<MainActivityViewModel>()

    private val categoryAdapter = CategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerCategory = findViewById<RecyclerView>(R.id.recycler_category)
        recyclerCategory.adapter = categoryAdapter

        //добавить через класс
        /*categoryList.add(Category("В наличии", R.drawable.rose_1))
        categoryList.add(Category("Хит продаж", R.drawable.rose_2))
        categoryList.add(Category("Розы", R.drawable.igles_blue))
        categoryList.add(Category("Пионы", R.drawable.rose_2))
        categoryList.add(Category("Микс", R.drawable.rose_1))
*/
        categoryAdapter.addCategory(viewModelMain.getCategory())
    }
}