package com.example.data.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var AUTH: FirebaseAuth

lateinit var storedVerificationId: String
lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

const val FOLDER_PROFILE_IMAGE = "images_of_flower"
const val NODE_FLOWERS = "all_flowers"
const val NODE_FLOWERS_CHILD = "flowers"
const val NODE_ALL_ARTICUL = "articuls"
const val CHILD_ARTICUL_FLOWER = "articul"
const val CHILD_NAME_FLOWER = "name"
const val CHILD_COST_FLOWER = "cost"
const val CHILD_ABOUT_FLOWER = "about"
const val CHILD_CATEGORY_FLOWER = "category"
const val CHILD_HAVE_FLOWER = "have"
const val CHILD_HIT_FLOWER = "hit"
const val CHILD_URL_FLOWER = "url"


fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
}