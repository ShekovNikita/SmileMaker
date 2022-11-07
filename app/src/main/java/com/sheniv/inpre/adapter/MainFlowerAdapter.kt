package com.sheniv.inpre.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sheniv.inpre.R
import com.sheniv.inpre.databinding.MainFlowersItemBinding
import com.sheniv.inpre.fragments.ChangeAmountFlowerInBasket
import com.sheniv.inpre.fragments.DeleteFlowerFromBasket
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.activityAboutFlower
import com.sheniv.inpre.utilits.basket
import com.sheniv.inpre.utilits.beVisible

class MainFlowerAdapter(
    private val context: Context,
    private val mainFlowerList: ArrayList<FlowerMain>,
    private val deleteFlowerFromBasket: DeleteFlowerFromBasket,
    private val changeAmountFlowerInBasket: ChangeAmountFlowerInBasket
) : RecyclerView.Adapter<MainFlowerAdapter.FlowerViewHolder>() {

    inner class FlowerViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val binding = MainFlowersItemBinding.bind(item)
        fun bind(flower: FlowerMain) = with(binding) {
            cost.text = flower.cost + " BYN"
            name.text = flower.name
            articul.text = "Артикул: ${flower.articul}"
            Glide.with(context)
                .load(flower.photos[0])
                .placeholder(R.drawable.logo_blue)
                .skipMemoryCache(true)
                .into(image)
            if (flower.hit == "hit") {
                hit.beVisible()
            }
            /*if (flower.have != "have") {
                have.visibility = View.VISIBLE
            }*/
            if (flower.amount > 0){
                checkbox.isChecked = true
            }
            checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                when(isChecked){
                    true -> {
                        flower.amount = 1
                        basket.changeAmountInBasket(flower)
                        //changeAmountFlowerInBasket.changeAmountOfFlowerInBasket(flower)
                    }
                    false -> {
                        //flower.amount = 0
                        basket.deleteFromBasket(flower)
                        //deleteFlowerFromBasket.deleteFlowerFromBasket(flower)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FlowerViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.main_flowers_item, parent, false)
        )

    override fun onBindViewHolder(holder: MainFlowerAdapter.FlowerViewHolder, position: Int) {
        holder.bind(mainFlowerList[position])
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.adapter)
        holder.itemView.setOnClickListener {
            activityAboutFlower(mainFlowerList[position])
        }
    }

    override fun getItemCount() = mainFlowerList.size
}