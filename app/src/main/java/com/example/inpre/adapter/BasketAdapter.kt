package com.example.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.BasketItemBinding
import com.example.inpre.fragments.ChangeAmount
import com.example.inpre.fragments.MainFlowerClick
import java.util.*
import kotlin.collections.ArrayList

class BasketAdapter(
    private val click: MainFlowerClick,
    private val basketList: ArrayList<Flower>,
    private val changeAmount: ChangeAmount,
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    class BasketViewHolder(item: View, private val changeAmount: ChangeAmount, private val click: MainFlowerClick) : RecyclerView.ViewHolder(item) {

        private val binding = BasketItemBinding.bind(item)

        fun bind(flower: Flower) = with(binding) {
            title.text = flower.title
            image.setImageResource(flower.image)
            cost.text = flower.cost + " BYN"
            counter.text = flower.amount.toString()
            btnPlus.setOnClickListener {
                flower.amount += 1
                changeAmount.addFlower(flower)
                counter.text = flower.amount.toString()
            }
            btnMinus.setOnClickListener {
                if (flower.amount > 0) {
                    flower.amount -= 1
                    changeAmount.addFlower(flower)
                    counter.text = flower.amount.toString()
                }
            }
            image.setOnClickListener {
                click.sendData(flower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BasketViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.basket_item, parent, false), changeAmount, click
        )

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList[position])
        holder.itemView.setOnLongClickListener {
            click.deleteFlower(basketList[position])
            true
        }
    }

    override fun getItemCount() = basketList.size
}