package com.sheniv.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sheniv.inpre.R
import com.sheniv.inpre.databinding.ChangeFlowerItemBinding
import com.sheniv.inpre.fragments.ChangeFlowerInFirebase
import com.sheniv.inpre.models.FlowerMain

class ChangeFlowerAdapter(
    private val context: Context,
    private val mainFlowerList: ArrayList<FlowerMain>,
    private val change: ChangeFlowerInFirebase
) : RecyclerView.Adapter<ChangeFlowerAdapter.FlowerHolder>() {

    inner class FlowerHolder(item: View) : RecyclerView.ViewHolder(item){
        private val binding = ChangeFlowerItemBinding.bind(item)
        fun bind(flower: FlowerMain) = with(binding) {
            cost.text = flower.cost + " BYN"
            name.text = flower.name
            articul.text = "Артикул: ${flower.articul}"
            Glide.with(context)
                .load(flower.photos[0])
                .placeholder(R.drawable.logo_blue)
                .skipMemoryCache(true)
                .into(image)

            btnChange.setOnClickListener { change.changeFlower(flower) }
            btnDelete.setOnClickListener { change.deleteFlower(flower.articul) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FlowerHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.change_flower_item, parent, false))


    override fun onBindViewHolder(holder: FlowerHolder, position: Int) {
        holder.bind(mainFlowerList[position])
    }

    override fun getItemCount() = mainFlowerList.size
}