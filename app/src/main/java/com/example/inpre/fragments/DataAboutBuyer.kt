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
    private var dostavka_pay = false

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDataAboutBuyerBinding.inflate(inflater, container, false)

    override fun FragmentDataAboutBuyerBinding.onBindView(savedInstanceState: Bundle?) {

        val summa = viewModel.getSumOfBasket()
        val skid = summa / 10

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
                    if (summa < 50){
                        dostavka.visibility = View.VISIBLE
                        dostavka.text = "Доставка: 5 BYN"
                        dostavka_pay = true
                    }
                }
                R.id.button_post -> {
                    cleanDataTable(group)
                    groupEuropost.visibility = View.VISIBLE
                    if (summa < 50){
                        order.isClickable = false
                        order.text = "Отправка почтой от 50 BYN"
                    }
                    dostavka.visibility = View.VISIBLE
                    dostavka.text = "Отправка: 5 BYN"
                    dostavka_pay = true
                }
            }
        }

        date.setOnClickListener {
            sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
            sumBonus.visibility = View.GONE
            skidka.visibility = View.GONE
            dostavka.visibility = View.GONE
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dpd = DatePickerDialog(requireContext(), { _, getYear, getMonth, getDay ->
                date.text = "$getDay ${getMonth + 1} $getYear"
                if (((getDay - day) > 1) || ((getDay - day) < 0) && ((getMonth - month) > 0)) {
                    sumBonus.visibility = View.VISIBLE
                    skidka.visibility = View.VISIBLE
                    sumBonus.text = "Цена со скидкой: ${summa - skid} BYN"
                    skidka.text = "Скидка: $skid BYN"
                    sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    if (dostavka_pay){
                        dostavka.visibility = View.VISIBLE
                        sumBonus.text = "Цена со скидкой: ${summa - skid + 5} BYN"
                    }
                } else {
                    if (dostavka_pay){
                        dostavka.visibility = View.VISIBLE
                        sumBonus.visibility = View.VISIBLE
                        sumBonus.text = "Итого: ${summa + 5} BYN"
                    }
                }
            }, year, month, day)
            when {
                buttonPickup.isChecked -> {
                    c.add(Calendar.DAY_OF_MONTH, 0)
                }
                buttonPost.isChecked -> {
                    c.add(Calendar.DAY_OF_MONTH, 7)
                }
                buttonOrder.isChecked -> {
                    c.add(Calendar.DAY_OF_MONTH, 0)
                }
            }
            dpd.datePicker.minDate = c.timeInMillis
            dpd.show()
        }

        order.setOnClickListener {
            val sb = StringBuffer()

            for (i in viewModel.getBasket()) {
                sb.append("Артикул: ${i.articul}\n" +
                        "Название: ${i.name}\n" +
                        "Количество: ${i.amount}\n" +
                        "Стоимость: ${i.cost.toInt() * i.amount}\n\n")
            }

            sb.append("\nЦена: ${viewModel.getSumOfBasket()} BYN")

            if (dostavka_pay){
                sb.append("\n${dostavka.text}")
            }

            if (skidka.visibility == View.VISIBLE) {
                sb.append("\n${skidka.text}")
            }

            if (sumBonus.visibility == View.VISIBLE) {
                sb.append("\n${sumBonus.text}")
            }

            val radioButton =
                root.findViewById<RadioButton>(radioGroup.checkedRadioButtonId).text.toString()
            sb.append("\nСпособ получения: $radioButton")

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
            dostavka.visibility = View.GONE
            sum.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
            dostavka_pay = false
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