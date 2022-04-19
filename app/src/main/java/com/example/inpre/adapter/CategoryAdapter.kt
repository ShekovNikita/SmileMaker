package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Category
import com.example.inpre.R
import com.example.inpre.databinding.CategoryItemBinding

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = ArrayList<Category>()

    class CategoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = CategoryItemBinding.bind(item)

        fun bind(category: Category) = with(binding) {
            textCategory.text = category.nameCategory
            imageCategory.setImageResource(category.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount() = categoryList.size

    fun addCategory(category: ArrayList<Category>) {
        categoryList.addAll(category)
    }
}