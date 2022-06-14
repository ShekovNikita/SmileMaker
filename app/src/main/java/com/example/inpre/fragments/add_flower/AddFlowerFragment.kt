package com.example.inpre.fragments.add_flower

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.activity.result.contract.ActivityResultContracts
import com.bumptech.glide.Glide
import com.example.data.firebase.*
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentAddFlowerBinding
import com.example.inpre.showToast

class AddFlowerFragment : BaseFragment<FragmentAddFlowerBinding>() {

    lateinit var top: Uri
    lateinit var side: Uri

    private val selectImageFromGalleryResultTop =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                top = uri
                Glide.with(requireActivity())
                    .load(uri)
                    .override(400, 400)
                    .centerCrop()
                    .into(binding.photoTop)
            }
        }

    private val selectImageFromGalleryResultSide =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                side = uri
                Glide.with(requireActivity())
                    .load(uri)
                    .override(400, 400)
                    .centerCrop()
                    .into(binding.photoSide)
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
            path.child("top").putFile(top).addOnCompleteListener { task_top ->
                if (task_top.isSuccessful) {
                    path.child("top").downloadUrl.addOnCompleteListener { tasks ->
                        if (tasks.isSuccessful) {
                            images_map.add(tasks.result.toString())
                            path.child("side").putFile(side).addOnCompleteListener { task_side ->
                                if (task_side.isSuccessful) {
                                    path.child("side").downloadUrl.addOnCompleteListener { taska ->
                                        if (taska.isSuccessful) {
                                            images_map.add(taska.result.toString())
                                            dataMap["photos"] = images_map
                                            REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
                                                .child(FLOWERS_NODE_CHILD)
                                                .child(articul)
                                                .updateChildren(dataMap)
                                                .addOnCompleteListener { task5 ->
                                                    if (task5.isSuccessful) {
                                                        REF_DATABASE_ROOT.child(ALL_FLOWERS_NODE)
                                                            .child(ARTICULS_NODE_CHILD)
                                                            .updateChildren(articulMap)
                                                            .addOnCompleteListener {
                                                                if (it.isSuccessful) {
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
            }
        }


        photoTop.setOnClickListener {
            selectImageFromGalleryResultTop.launch("image/*")
        }

        photoSide.setOnClickListener {
            selectImageFromGalleryResultSide.launch("image/*")
        }
    }

}