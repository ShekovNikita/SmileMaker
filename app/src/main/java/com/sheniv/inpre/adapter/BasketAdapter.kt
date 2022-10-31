package com.sheniv.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.exoplayer2.Player
import com.sheniv.inpre.R
import com.sheniv.inpre.databinding.BasketItemBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.activityAboutFlower
import com.sheniv.inpre.utilits.basket


class BasketAdapter(
    private val context: Context,
    private val changeAmountFlowerInBasket: ChangeAmountFlowerInBasket,
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(
        item: View,
    ) : RecyclerView.ViewHolder(item) {

        private val binding = BasketItemBinding.bind(item)

        val del = binding.btnDelete

        fun bind(flower: FlowerMain) = with(binding) {
            title.text = flower.name
            Glide.with(context).load(flower.photos[0]).into(image)
            cost.text = flower.cost + " BYN"
            articul.text = "Артикул: ${flower.articul}"
            counter.text = flower.amount.toString()
            btnPlus.setOnClickListener {
                flower.amount += 1
                basket.changeAmountInBasket(flower)
                counter.text = flower.amount.toString()
                changeAmountFlowerInBasket.changeAmountFlowerInBasket()
            }
            btnMinus.setOnClickListener {
                if (flower.amount > 0) {
                    flower.amount -= 1
                    basket.changeAmountInBasket(flower)
                    counter.text = flower.amount.toString()
                    changeAmountFlowerInBasket.changeAmountFlowerInBasket()
                }
            }
            image.setOnClickListener {
                activityAboutFlower(flower)
            }
            btnDelete.setOnClickListener {
                basket.deleteFromBasket(flower)
                changeAmountFlowerInBasket.changeAmountFlowerInBasket()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BasketViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.basket_item, parent, false)
        )

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.adapter)
        holder.setIsRecyclable(true)
    }

    override fun getItemCount() = differ.currentList.size

    private val differCallback = object : DiffUtil.ItemCallback<FlowerMain>(){
        override fun areItemsTheSame(oldItem: FlowerMain, newItem: FlowerMain): Boolean {
            return oldItem.articul == newItem.articul
        }

        override fun areContentsTheSame(oldItem: FlowerMain, newItem: FlowerMain): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    fun deleteItem(pos: Int) {
        basket.deleteFromBasket(differ.currentList[pos])
        changeAmountFlowerInBasket.changeAmountFlowerInBasket()
    }
}