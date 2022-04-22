package com.example.inpre.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inpre.R
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentDataAboutBuyerBinding
import com.example.inpre.viewmodel.DataAboutBuyerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class DataAboutBuyer : BaseFragment<FragmentDataAboutBuyerBinding>() {

    private val viewModel by viewModel<DataAboutBuyerViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDataAboutBuyerBinding =
        FragmentDataAboutBuyerBinding.inflate(inflater, container, false)

    override fun FragmentDataAboutBuyerBinding.onBindView(savedInstanceState: Bundle?) {

        val group = listOf(groupPickup, groupOrder, groupEuropost)

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
                    groupEuropost.visibility = View.VISIBLE
                }
            }
        }

        date.setOnClickListener {
            val c = Calendar.getInstance()
            c.add(Calendar.DAY_OF_MONTH, 0)
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { _, i, i2, i3 ->
                date.text = "$i3 ${i2+1} $i"
                if(((i3-day) > 1) || ((i3-day) < 0) && ((i2-month) > 0)){
                    sum.text = "Цена со скидкой: sdasd BYN"
                } else {
                    sum.text = "Цена: дывда BYN"
                }
            }, year, month, day)
            dpd.datePicker.minDate = c.timeInMillis
            dpd.show()
        }

        order.setOnClickListener {
            val sb = StringBuffer()
            for (i in viewModel.getBasket()){
                sb.append("Название: ${i.title}\nКоличество: ${i.amount} \n\n")
            }
            sb.append("\nсюда добавить сумму")
            sb.append("\n${name.text}")
            sb.append("\nТелефон: ${phone.text}")
            sb.append("\nЗаказано к ${date.text}")
            composeEmail(sb.toString())
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