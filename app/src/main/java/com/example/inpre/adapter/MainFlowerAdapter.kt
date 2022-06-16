package com.example.inpre.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.model.Flower
import com.example.inpre.R
import com.example.inpre.databinding.MainFlowersItemBinding
import com.example.inpre.fragments.ChangeAmount
import com.example.inpre.fragments.DeleteFlower
import com.example.inpre.fragments.MainFlowerClick

class MainFlowerAdapter(
    private val context: Context,
    private val click: MainFlowerClick,
    private val mainFlowerList: ArrayList<Flower>,
    private val deleteFlower: DeleteFlower,
    private val changeAmount: ChangeAmount
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
            if (flower.hit == "hit") {
                hit.visibility = View.VISIBLE
            }
            if (flower.have == "have") {
                have.visibility = View.VISIBLE
            }
            if (flower.amount > 0){
                checkbox.isChecked = true
                Log.e("chek", "${checkbox.isChecked}")
            }
            checkbox.setOnCheckedChangeListener { compoundButton, isChecked ->
                when(isChecked){
                    true -> {
                        flower.amount = 1
                        changeAmount.addFlower(flower)
                    }
                    false -> {
                        flower.amount = 0
                        deleteFlower.deleteFlower(flower)
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
        holder.itemView.setOnClickListener {
            click.sendData(mainFlowerList[position])
        }
    }

    override fun getItemCount() = mainFlowerList.size
}