package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inpre.R
import com.example.inpre.databinding.CategoryItemBinding
import com.example.domain.model.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    val categoryList = ArrayList<Category>()

    class CategoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = CategoryItemBinding.bind(item)

        fun bind(category: Category) = with(binding) {
            textCategory.text = category.category
            imageCategory.setImageResource(category.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addCategory(category: ArrayList<Category>) {
        categoryList.addAll(category)
    }
}