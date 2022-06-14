package com.example.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.inpre.R

class ViewPagerAdapter(private val photos: List<Any>) : RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    class PagerVH(item: View) : RecyclerView.ViewHolder(item)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH =
        PagerVH(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount(): Int = photos.size

    override fun onBindViewHolder(holder: PagerVH, position: Int): Unit = holder.itemView.run {
        val image = findViewById<ImageView>(R.id.imageViewMain)
        Glide.with(context)
            .load(photos[position])
            .skipMemoryCache(true)
            .into(image)
    }
}