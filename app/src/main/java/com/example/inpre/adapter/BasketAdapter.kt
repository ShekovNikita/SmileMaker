package com.example.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.BasketItemBinding
import com.example.inpre.fragments.ChangeAmount
import com.example.inpre.fragments.DeleteFlower
import com.example.inpre.fragments.MainFlowerClick


class BasketAdapter(
    private val context: Context,
    private val click: MainFlowerClick,
    private val basketList: ArrayList<Flower>,
    private val changeAmount: ChangeAmount,
    private val deleteFlower: DeleteFlower
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(
        item: View,
        private val changeAmount: ChangeAmount,
        private val click: MainFlowerClick
    ) : RecyclerView.ViewHolder(item) {

        private val binding = BasketItemBinding.bind(item)

        fun bind(flower: Flower) = with(binding) {
            title.text = flower.name
            Glide.with(context).load(flower.img_source).into(image)
            cost.text = flower.cost + " BYN"
            articul.text = "Артикул: ${flower.articul}"
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
            btnDelete.setOnClickListener {
                deleteFlower.deleteFlower(flower)
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
    }

    override fun getItemCount() = basketList.size

    fun deleteItem(pos: Int) {
        deleteFlower.deleteFlower(basketList[pos])
        basketList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}