package com.sheniv.inpre

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sheniv.inpre.adapter.ReviewAdapter
import com.sheniv.inpre.adapter.ViewPagerAdapter
import com.sheniv.inpre.databinding.ActivityAboutFlowerBinding
import com.sheniv.inpre.firebase.*
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.models.Review
import com.sheniv.inpre.utilits.*
import com.sheniv.inpre.viewmodels.AboutFlowerActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AboutFlowerActivity : AppCompatActivity() {

    private val viewModel by viewModel<AboutFlowerActivityViewModel>()

    private var _binding: ActivityAboutFlowerBinding? = null
    private val binding get() = _binding!!

    lateinit var flower: FlowerMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutFlowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        flower = intent.getSerializableExtra("flower") as FlowerMain
        //binding.recyclerReview.adapter = ReviewAdapter(getReviews())
        getReviews()

        val size = Resources.getSystem().displayMetrics.widthPixels;
        val params: ViewGroup.LayoutParams = binding.viewPager2.layoutParams
        params.height = size
        binding.viewPager2.layoutParams = params

        binding.viewPager2.adapter = ViewPagerAdapter(flower.photos)

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tab, position ->
        }.attach()

        if(USER.name == "" && AUTH.currentUser != null) { dialogName() }

        addFlower(flower)
        deleteFlower(flower)

        with(binding) {
            cost.text = "${flower.cost} BYN"
            textFlower.text = flower.name
            articulFlower.text = "Артикул: ${flower.articul}"
        }

        if (viewModel.getBasket().contains(flower)) {
            deleteFlower(flower)
        } else addFlower(flower)

        binding.reviewTextLayout.setEndIconOnClickListener {
            when {
                binding.reviewText.text!!.isEmpty() -> {
                    showToast("Отзыв не может быть пустым")
                }
                AUTH.currentUser == null -> {
                    showToast("Отзывы могут оставлять только зарегистрированные пользователи")
                }
                USER.name == "" && AUTH.currentUser != null -> dialogName()
                else -> {
                    sendReview(
                        Review(
                            name = USER.name,
                            articul = flower.articul,
                            review = binding.reviewText.text.toString(),
                            phone = USER.phone
                        )
                    )
                    showToast("Спасибо за отзыв")
                    binding.reviewText.setText("")
                }
            }
        }

    }

    private fun dialogName() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_user_name, null)
        val name: EditText = dialogView.findViewById<EditText>(R.id.input_text)
        val builder = android.app.AlertDialog.Builder(this).setView(dialogView)
        builder.setPositiveButton("Принять") { _, _ ->
            if (name.text.toString().isEmpty()) {
                showToast("Имя не может быть пустым")
            } else {
                REF_DATABASE_ROOT
                    .child(NODE_USER)
                    .child(USER.id)
                    .updateChildren(mapOf(CHILD_NAME to name.text.toString()))
                initUser()
            }
        }
        builder.create().show()
    }

    private fun getReviews(): ArrayList<Review> {
        val reviews = arrayListOf<Review>()
        REF_DATABASE_ROOT.child(NODE_REVIEWS).child(flower.articul)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        reviews.clear()
                        for (favorite in snapshot.children) {
                            reviews.add(
                                favorite.getValue(Review::class.java) ?: Review()
                            )
                            Log.e("time", "${favorite.child(CHILD_DATE).value.toString().asTime()}")
                        }
                    }
                    binding.recyclerReview.adapter = ReviewAdapter(reviews)
                    if (reviews.size == 0){
                        binding.textReview.text = "Отзывов еще нет"
                    } else binding.textReview.text = "Отзывы покупателей:"
                    Log.e("REVIEWS", "$reviews")
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })
        return reviews
    }

    private fun deleteFlower(flower: FlowerMain) {
        binding.buttonBasket.visibility = View.INVISIBLE
        binding.buttonBasketDelete.beVisible()
        binding.buttonBasketDelete.setOnClickListener {
            viewModel.deleteFlower(flower)
            showToast("Букет удалён из корзины")
            addFlower(flower)
        }
    }

    private fun addFlower(flower: FlowerMain) {
        binding.buttonBasket.beVisible()
        binding.buttonBasketDelete.beGone()
        binding.buttonBasket.setOnClickListener {
            flower.amount += 1
            viewModel.addToBasket(flower)
            showToast("Букет добавлен в корзину")
            deleteFlower(flower)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
