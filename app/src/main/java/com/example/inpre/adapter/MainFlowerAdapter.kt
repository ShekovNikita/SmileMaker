package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.inpre.fragments.MainFlowerClick
import com.example.inpre.R
import com.example.inpre.databinding.MainFlowersItemBinding
import com.example.domain.model.Flower

class MainFlowerAdapter(
    private val click: MainFlowerClick,
    private val mainFlowerList: ArrayList<Flower>
) : RecyclerView.Adapter<MainFlowerAdapter.FlowerViewHolder>() {
    //private val mainFlowerList = ArrayList<Flower>()

    private class Diff : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean = when {
            oldItem is Flower && newItem is Flower -> oldItem == newItem
            else -> false
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean = when {
            oldItem is Flower && newItem is Flower -> oldItem.title == newItem.title
            else -> false
        }
    }

    class FlowerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val binding = MainFlowersItemBinding.bind(item)
        fun bind(flower: Flower) = with(binding) {
            cost.text = flower.cost +" BYN"
            image.setImageResource(flower.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        return FlowerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.main_flowers_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(mainFlowerList[position])
        holder.itemView.setOnClickListener {
            click.sendData(mainFlowerList[position])

        }
    }

    override fun getItemCount()= mainFlowerList.size

    fun addCategory(flower: ArrayList<Flower>) {
        if (mainFlowerList != flower) {
            mainFlowerList.addAll(flower)
        }
    }
}