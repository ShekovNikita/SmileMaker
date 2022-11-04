package com.sheniv.inpre.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.sheniv.inpre.models.User

lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var CURRENT_UID: String
lateinit var AUTH: FirebaseAuth
lateinit var USER: User

lateinit var storedVerificationId: String
lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

const val NODE_PHONES = "phones"
const val NODE_USER = "users"
const val CHILD_ID = "id"
const val CHILD_PHONE = "phone"
const val CHILD_NAME = "name"
const val IMAGE_OF_FLOWERS = "images_of_flower"
const val ALL_FLOWERS_NODE = "all_flowers"
const val FLOWERS_NODE_CHILD = "flowers"
const val ARTICULS_NODE_CHILD = "articuls"
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

fun initUser(){
    USER = User()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    USER.phone = AUTH.currentUser?.phoneNumber.toString()
    USER.id = AUTH.currentUser?.uid.toString()
}