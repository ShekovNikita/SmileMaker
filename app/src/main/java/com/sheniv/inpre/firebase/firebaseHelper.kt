package com.sheniv.inpre.firebase

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ServerValue
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sheniv.inpre.models.Review
import com.sheniv.inpre.models.User
import com.sheniv.inpre.utilits.APP_ACTIVITY
import com.sheniv.inpre.utilits.AppValueEventListener
import com.sheniv.inpre.utilits.showToast

lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var CURRENT_UID: String
lateinit var AUTH: FirebaseAuth
lateinit var USER: User

lateinit var storedVerificationId: String
lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

const val NODE_PHONES = "phones"
const val NODE_USER = "users"
const val NODE_REVIEWS = "reviews"
const val FLOWERS_NODE_CHILD = "flowers"

const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_NAME = "name"
const val IMAGE_OF_FLOWERS = "images_of_flower"
const val ALL_FLOWERS_NODE = "all_flowers"
const val CHILD_ARTICUL_FLOWER = "articul"
const val CHILD_NAME_FLOWER = "name"
const val CHILD_COST_FLOWER = "cost"
const val CHILD_CATEGORY_FLOWER = "category"
const val CHILD_HAVE_FLOWER = "have"
const val CHILD_HIT_FLOWER = "hit"
const val CHILD_URL_FLOWER = "url"
const val CHILD_REVIEW = "review"
const val CHILD_DATE = "date"

fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}

fun initUser() {
    USER = User()
    USER.phone = AUTH.currentUser?.phoneNumber.toString()
    USER.id = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT.child(NODE_USER).child(USER.id)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(User::class.java) ?: User()
            Log.e("USER", "$USER")
        })
}

fun sendReview(review: Review) {
    REF_DATABASE_ROOT.child(NODE_REVIEWS).child(review.articul).child(USER.id)
        .updateChildren(
            mapOf(
                CHILD_NAME to review.name,
                CHILD_PHONE to review.phone,
                CHILD_DATE to ServerValue.TIMESTAMP,
                CHILD_REVIEW to review.review,
                CHILD_ID to USER.id,
                CHILD_ARTICUL_FLOWER to review.articul
            )
        )
}

fun deleteReview(review: Review) {
    REF_DATABASE_ROOT.child(NODE_REVIEWS)
        .child(review.articul)
        .child(review.id)
        .removeValue()
        .addOnCompleteListener { APP_ACTIVITY.showToast("Комментарий удалён") }
        .addOnFailureListener { APP_ACTIVITY.showToast(it.message.toString()) }
}