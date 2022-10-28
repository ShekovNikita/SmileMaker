package com.sheniv.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sheniv.domain.model.Flower
import com.sheniv.inpre.R
import com.sheniv.inpre.databinding.BasketItemBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.fragments.MainFlowerClick


class BasketAdapter(
    private val context: Context,
    private val click: MainFlowerClick,
    private val basketList: ArrayList<Flower>,
    private val changeAmountFlowerInBasket: ChangeAmountFlowerInBasket,
    private val deleteFlowerFromBasket: DeleteFlowerFromBasket
) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {

    inner class BasketViewHolder(
        item: View,
        private val changeAmountFlowerInBasket: ChangeAmountFlowerInBasket,
        private val click: MainFlowerClick
    ) : RecyclerView.ViewHolder(item) {

        private val binding = BasketItemBinding.bind(item)

        fun bind(flower: Flower) = with(binding) {
            title.text = flower.name
            Glide.with(context).load(flower.img_source[0]).into(image)
            cost.text = flower.cost + " BYN"
            articul.text = "Артикул: ${flower.articul}"
            counter.text = flower.amount.toString()
            btnPlus.setOnClickListener {
                flower.amount += 1
                changeAmountFlowerInBasket.changeAmountOfFlowerInBasket(flower)
                counter.text = flower.amount.toString()
            }
            btnMinus.setOnClickListener {
                if (flower.amount > 0) {
                    flower.amount -= 1
                    changeAmountFlowerInBasket.changeAmountOfFlowerInBasket(flower)
                    counter.text = flower.amount.toString()
                }
            }
            image.setOnClickListener {
                click.sendData(flower)
            }
            btnDelete.setOnClickListener {
                flower.amount = 0
                deleteFlowerFromBasket.deleteFlowerFromBasket(flower)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BasketViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.basket_item, parent, false), changeAmountFlowerInBasket, click
        )

    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketList[position])
    }

    override fun getItemCount() = basketList.size

    fun deleteItem(pos: Int) {
        basketList[pos].amount = 0
        deleteFlowerFromBasket.deleteFlowerFromBasket(basketList[pos])
        basketList.removeAt(pos)
        notifyItemRemoved(pos)
    }
}