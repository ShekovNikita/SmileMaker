package com.example.inpre.fragments.add_flower

import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.viewpager2.widget.ViewPager2
import com.example.data.firebase.*
import com.example.inpre.R
import com.example.inpre.adapter.ViewPagerAdapter
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentAddFlowerBinding
import com.example.inpre.showToast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFlowerFragment : BaseFragment<FragmentAddFlowerBinding>() {

    private var topList: List<Uri> = listOf()
    lateinit var viewPager2: ViewPager2

    var selectImage: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uri: List<Uri> ->
            uri.let {
                topList = uri
                println("----------------------------------toplist    $topList")
                viewPager2 = requireView().findViewById(R.id.viewPager2)
                val tabLayout = requireView().findViewById<TabLayout>(R.id.tab_layout)

                val size = Resources.getSystem().displayMetrics.widthPixels;
                val params: ViewGroup.LayoutParams = viewPager2.layoutParams
                params.height = size
                viewPager2.layoutParams = params

                viewPager2.adapter = ViewPagerAdapter(topList)
                TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                    tab.text = "ФОТО ${(position + 1)}"
                }.attach()
            }
        }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAddFlowerBinding = FragmentAddFlowerBinding.inflate(inflater, container, false)


    override fun FragmentAddFlowerBinding.onBindView(savedInstanceState: Bundle?) {

        ok.setOnClickListener {
            val dataMap: MutableMap<String, Any> = mutableMapOf()
            val articulMap: MutableMap<String, Any> = mutableMapOf()
            val articul = flowerArticul.text.toString()
            dataMap[CHILD_ARTICUL_FLOWER] = articul
            dataMap[CHILD_NAME_FLOWER] = flowerName.text.toString()
            dataMap[CHILD_COST_FLOWER] = flowerCost.text.toString()
            dataMap[CHILD_ABOUT_FLOWER] = flowerAbout.text.toString()
            val images_map: MutableList<String> = mutableListOf()

            val radioButton = root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            dataMap[CHILD_CATEGORY_FLOWER] = radioButton.text.toString()

            var have = ""
            if (flowerHave.isChecked) {
                have = "have"
            }
            dataMap[CHILD_HAVE_FLOWER] = have

            var hit = ""
            if (flowerHit.isChecked) {
                hit = "hit"
            }
            dataMap[CHILD_HIT_FLOWER] = hit
            articulMap[articul] = articul

            val path = REF_STORAGE_ROOT.child(IMAGE_OF_FLOWERS).child(articul)
            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
                .child(ARTICULS_NODE_CHILD)
                .updateChildren(articulMap)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        for ((index, value) in topList.withIndex()) {
                            path.child(index.toString()).putFile(value)
                                .addOnCompleteListener { task_top ->
                                    if (task_top.isSuccessful) {
                                        path.child(index.toString()).downloadUrl.addOnCompleteListener { tasks ->
                                            if (tasks.isSuccessful) {
                                                images_map.add(tasks.result.toString())
                                                showToast("${index + 1}-e фото добавлено")
                                                if (images_map.size == topList.size) {
                                                    println("image_map ${images_map.size} \n topList ${topList.size}")
                                                    dataMap["photos"] = images_map
                                                    REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
                                                        .child(FLOWERS_NODE_CHILD)
                                                        .child(articul)
                                                        .updateChildren(dataMap)
                                                        .addOnCompleteListener { task5 ->
                                                            if (task5.isSuccessful) {
                                                                showToast("Добавлен цветок")
                                                                println("------------------------------$dataMap")
                                                            }
                                                        }
                                                }
                                            }
                                        }
                                    }
                                }
                        }
                    }
                }
        }

        addPhotos.setOnClickListener {
            selectImage.launch("image/*")
        }
    }
}