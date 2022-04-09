package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.BasketItemBinding

class BasketAdapter(private val basketList: ArrayList<Flower>): RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = BasketItemBinding.bind(item)

        fun bind(flower: Flower) = with(binding) {
            title.text = flower.title
            image.setImageResource(flower.image)
            cost.text = flower.cost + " BYN"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        return BasketViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.basket_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount() = basketList.size
}