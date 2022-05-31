package com.example.inpre.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.example.inpre.R
import com.example.inpre.base.BaseFragment
import com.example.inpre.databinding.FragmentDataAboutBuyerBinding
import com.example.inpre.viewmodels.DataAboutBuyerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class DataAboutBuyer : BaseFragment<FragmentDataAboutBuyerBinding>() {

    private val viewModel by viewModel<DataAboutBuyerViewModel>()

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDataAboutBuyerBinding.inflate(inflater, container, false)

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

        val summa = viewModel.getSumOfBasket()

        date.setOnClickListener {
            sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
            sumBonus.visibility = View.GONE
            skidka.visibility = View.GONE
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { _, i, i2, i3 ->
                date.text = "$i3 ${i2 + 1} $i"
                if (((i3 - day) > 1) || ((i3 - day) < 0) && ((i2 - month) > 0)) {
                    sumBonus.visibility = View.VISIBLE
                    skidka.visibility = View.VISIBLE
                    val skid = summa / 10
                    sumBonus.text = "Цена со скидкой: ${summa - skid} BYN"
                    skidka.text = "Скидка: ${summa / 10} BYN"
                    sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                }
            }, year, month, day)
            if (buttonPickup.isChecked) {
                c.add(Calendar.DAY_OF_MONTH, 0)
            } else if (buttonPost.isChecked) {
                c.add(Calendar.DAY_OF_MONTH, 7)
            }
            dpd.datePicker.minDate = c.timeInMillis
            dpd.show()
        }

        order.setOnClickListener {
            val sb = StringBuffer()

            for (i in viewModel.getBasket()) {
                sb.append("Артикул: ${i.articul}\nНазвание: ${i.name}\nКоличество: ${i.amount}\nСтоимость: ${i.cost.toInt() * i.amount}\n\n")
            }

            sb.append("\nЦена: ${viewModel.getSumOfBasket()} BYN")

            if (skidka.visibility == View.VISIBLE) {
                sb.append("\n${sumBonus.text}")
            }

            val radioButton =
                root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
            sb.append("\n$radioButton")

            if (radioButton == "Доставка") {
                sb.append("\nАдрес доставки: ${address.text}")
            }

            if (radioButton == "Европочта") {
                sb.append("\nАдрес Европочты: ${europost.text}")
            }

            sb.append("\n${name.text} ${surname.text}")
            sb.append("\nТелефон: ${phone.text}")
            sb.append("\nЗаказано к ${date.text}")
            composeEmail(sb.toString())
        }
    }

    private fun cleanDataTable(arrayViews: List<View>) {
        for (i in arrayViews) {
            i.visibility = View.GONE
        }

        with(binding) {
            date.text = ""
            sum.text = "Цена: ${viewModel.getSumOfBasket()} BYN"
            sumBonus.visibility = View.GONE
            skidka.visibility = View.GONE
            sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    private fun composeEmail(message: String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("smilemaker.by@gmail.com"))
            putExtra(Intent.EXTRA_SUBJECT, "theme")
            putExtra(Intent.EXTRA_TEXT, message)
        }
        startActivity(intent)
    }
}