package com.sheniv.inpre.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.ui.AppBarConfiguration
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.sheniv.inpre.R
import com.sheniv.inpre.firebase.ALL_FLOWERS_NODE
import com.sheniv.inpre.firebase.FLOWERS_NODE_CHILD
import com.sheniv.inpre.firebase.REF_DATABASE_ROOT
import com.sheniv.inpre.firebase.initFirebase
import com.sheniv.inpre.models.Category
import com.sheniv.inpre.models.FlowerMain
import com.sheniv.inpre.utilits.allFlowers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel() : ViewModel() {

    init {

        initFirebase()
        viewModelScope.launch(Dispatchers.Main) {
            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE).child(FLOWERS_NODE_CHILD)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        allFlowers.clear()
                        if (snapshot.exists()) {
                            for (favorite in snapshot.children) {
                                allFlowers.add(
                                    favorite.getValue(FlowerMain::class.java) ?: FlowerMain()
                                )
                            }
                        }
                        //_basketLiveData.postValue(allFlowers)
                        Log.e("flowers", "$allFlowers")
                    }

                    override fun onCancelled(error: DatabaseError) {
                    }
                })
        }

    }

    private val arrayFlowerCategory = mutableListOf<Category>()

    fun getCategory(): ArrayList<Category> {
        addToArray()
        return arrayFlowerCategory as ArrayList<Category>
    }

    private fun addToArray() {
        arrayFlowerCategory.add(
            Category(
                "?? ??????????????",
                "https://smilemaker.by/wp-content/uploads/2022/01/photo_2022-01-17_19-13-52.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "????????",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-38-17-600x600.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "????????",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-50-00.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "??????????",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_15-15-29-600x600.jpg"
            )
        )
        arrayFlowerCategory.add(
            Category(
                "????????",
                "https://smilemaker.by/wp-content/uploads/2021/09/photo_2021-10-20_14-51-01.jpg"
            )
        )
    }

    fun getAppBarConfiguration(): AppBarConfiguration {
        return AppBarConfiguration(
            setOf(
                R.id.navigation_main,
                R.id.navigation_basket,
                R.id.navigation_contacts,
                R.id.navigation_info,
            )
        )
    }
}