package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.MainFlowersItemBinding
import com.example.inpre.fragments.MainFlowerClick

class MainFlowerAdapter(
    private val click: MainFlowerClick,
    private val mainFlowerList: ArrayList<Flower>
) : RecyclerView.Adapter<MainFlowerAdapter.FlowerViewHolder>() {

    class FlowerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = MainFlowersItemBinding.bind(item)
        fun bind(flower: Flower) = with(binding) {
            cost.text = flower.cost + " BYN"
            image.setImageResource(flower.image)
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