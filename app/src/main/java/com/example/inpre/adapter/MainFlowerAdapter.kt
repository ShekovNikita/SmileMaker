package com.example.inpre.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.MainFlowersItemBinding
import com.example.inpre.fragments.MainFlowerClick

class MainFlowerAdapter(
    private val context: Context,
    private val click: MainFlowerClick,
    private val mainFlowerList: ArrayList<Flower>
) : RecyclerView.Adapter<MainFlowerAdapter.FlowerViewHolder>() {

    inner class FlowerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = MainFlowersItemBinding.bind(item)
        fun bind(flower: Flower) = with(binding) {
            cost.text = flower.cost + " BYN"
            name.text = flower.name
            articul.text = "Артикул: ${flower.articul}"
            Glide.with(context)
                .load(flower.img_source[0])
                .placeholder(R.drawable.logo_blue)
                .skipMemoryCache(true)
                .into(image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FlowerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_flowers_item, parent, false)
        )

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(mainFlowerList[position])
        holder.itemView.setOnClickListener {
            click.sendData(mainFlowerList[position])
        }
    }

    override fun getItemCount() = mainFlowerList.size
}