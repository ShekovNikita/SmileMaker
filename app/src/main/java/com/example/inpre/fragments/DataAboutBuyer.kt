package com.example.inpre.fragments

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpre.R
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentDataAboutBuyerBinding
import java.text.SimpleDateFormat
import java.util.*


class DataAboutBuyer : BaseFragment<FragmentDataAboutBuyerBinding>() {

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDataAboutBuyerBinding =
        FragmentDataAboutBuyerBinding.inflate(inflater, container, false)

    override fun FragmentDataAboutBuyerBinding.onBindView(savedInstanceState: Bundle?) {

        val group = listOf(groupPickup, groupOrder, groupPost, groupSdek)

        radioGroup.setOnCheckedChangeListener { _, id ->
            when (id) {
                R.id.button_pickup -> {
                    cleanDataTable(group)
                    groupPickup.visibility = View.VISIBLE
                }
                R.id.button_order -> {
                    cleanDataTable(group)
                    groupOrder.visibility = View.VISIBLE
                }
                R.id.button_post -> {
                    cleanDataTable(group)
                    groupPost.visibility = View.VISIBLE
                }
                R.id.button_sdek -> {
                    cleanDataTable(group)
                    groupSdek.visibility = View.VISIBLE
                }
            }
        }

        date.setOnClickListener {
            val c = Calendar.getInstance()
            c.add(Calendar.DAY_OF_MONTH, 3)
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { _, i, i2, i3 ->
                date.text = "$i3-${i2 + 1}-$i"
            }, year, month, day)
            dpd.datePicker.minDate = c.timeInMillis
            dpd.show()
        }

        order.setOnClickListener {
            /*val sb = StringBuffer()
            for (i in viewModel.getBasket()){
                sb.append("Название: ${i.title}\nКоличество: ${i.amount} \n\n")
            }
            sb.append("\n${summa.text}")
            composeEmail(sb.toString())*/
        }
    }

    private fun cleanDataTable(arrayViews: List<View>){
        for(i in arrayViews){
            i.visibility = View.GONE
        }
    }

    private fun composeEmail(message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:") // only email apps should handle this
            putExtra(Intent.EXTRA_EMAIL, arrayOf("shekovnikita8@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "theme")
            putExtra(Intent.EXTRA_TEXT, message)
        }
        startActivity(intent)
    }
}