package com.sheniv.inpre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sheniv.inpre.R
import com.sheniv.inpre.adapter.ReviewAdapter.ReviewHolder
import com.sheniv.inpre.databinding.ItemReviewBinding
import com.sheniv.inpre.firebase.USER
import com.sheniv.inpre.firebase.deleteReview
import com.sheniv.inpre.models.Review
import com.sheniv.inpre.utilits.asTime
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible

class ReviewAdapter(
    private val reviews: ArrayList<Review>
) : RecyclerView.Adapter<ReviewHolder>() {

    inner class ReviewHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemReviewBinding.bind(item)
        fun bind(review: Review) = with(binding) {
            phoneNumber.text = review.name
            date.text = review.date.toString().asTime()
            textReview.text = review.review
            if (USER.phone == "+16505552494"){
                deleteReview.beVisible()
                deleteReview.setOnClickListener { deleteReview(review) }
            } else { deleteReview.beGone() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ReviewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_review, parent, false)
        )

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    override fun getItemCount() = reviews.size
}