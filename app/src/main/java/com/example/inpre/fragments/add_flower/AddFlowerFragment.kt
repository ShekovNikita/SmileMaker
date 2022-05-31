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

    lateinit var don: Uri

    private val selectImageFromGalleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                don = uri
                Glide.with(requireActivity())
                    .load(uri)
                    .override(500, 500)
                    .centerCrop()
                    .into(binding.photo)
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

            val path = REF_STORAGE_ROOT.child(FOLDER_PROFILE_IMAGE).child(articul)
            path.putFile(don).addOnCompleteListener { task1 ->
                if (task1.isSuccessful) {
                    path.downloadUrl.addOnCompleteListener { task2 ->
                        if (task2.isSuccessful) {
                            dataMap[CHILD_URL_FLOWER] = task2.result.toString()
                            REF_DATABASE_ROOT.child(NODE_FLOWERS).child(NODE_FLOWERS_CHILD)
                                .child(articul).updateChildren(dataMap)
                                .addOnCompleteListener { task3 ->
                                    if (task3.isSuccessful) {
                                        REF_DATABASE_ROOT.child(NODE_FLOWERS)
                                            .child(NODE_ALL_ARTICUL).updateChildren(articulMap)
                                            .addOnCompleteListener {
                                                if (it.isSuccessful) {
                                                    showToast("Добавлен цветок")
                                                }
                                            }
                                    }
                                }
                        }
                    }
                }
            }
        }

        photo.setOnClickListener {
            selectImageFromGallery()
        }
    }

    private fun selectImageFromGallery() = selectImageFromGalleryResult.launch("image/*")
}