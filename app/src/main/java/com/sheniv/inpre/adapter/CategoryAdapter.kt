package com.sheniv.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sheniv.inpre.R
import com.sheniv.inpre.databinding.CategoryItemBinding
import com.sheniv.inpre.fragments.CategoryClick
import com.sheniv.inpre.models.Category

class CategoryAdapter(private val context: Context, private val categoryClick: CategoryClick) :
    RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = ArrayList<Category>()

    inner class CategoryViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = CategoryItemBinding.bind(item)
        fun bind(category: Category) = with(binding) {
            textCategory.text = category.nameCategory
            Glide.with(context).load(category.image).into(imageCategory)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoryViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.category_item, parent, false)
        )

    var index = -1

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
        holder.itemView.setOnClickListener {
            categoryClick.choiceCategory(categoryList[position].nameCategory)
            index = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = categoryList.size

    fun addCategory(category: ArrayList<Category>) {
        categoryList.addAll(category)
    }
}