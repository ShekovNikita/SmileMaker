package com.sheniv.inpre.fragments

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import com.sheniv.inpre.R
import com.sheniv.inpre.base.BaseFragment
import com.sheniv.inpre.databinding.FragmentDataAboutBuyerBinding
import com.sheniv.inpre.utilits.beGone
import com.sheniv.inpre.utilits.beVisible
import com.sheniv.inpre.utilits.recyclerTop
import com.sheniv.inpre.utilits.showToast
import com.sheniv.inpre.viewmodels.DataAboutBuyerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.definition.IndexKey
import java.text.SimpleDateFormat
import java.util.*


class DataAboutBuyer : BaseFragment<FragmentDataAboutBuyerBinding>() {

    private val viewModel by viewModel<DataAboutBuyerViewModel>()
    private var dostavka_pay = false

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentDataAboutBuyerBinding.inflate(inflater, container, false)

    override fun onResume() {
        super.onResume()
        recyclerTop.beGone()
    }

    override fun FragmentDataAboutBuyerBinding.onBindView(savedInstanceState: Bundle?) {

        val summa = viewModel.getSumOfBasket()
        val skid = summa / 10

        val group = listOf(groupPickup, groupOrder, groupEuropost)
        radioGroup.setOnCheckedChangeListener { _, id ->
            groupRequired.beVisible()
            when (id) {
                R.id.button_pickup -> {
                    cleanDataTable(group)
                    groupPickup.beVisible()
                }
                R.id.button_order -> {
                    cleanDataTable(group)
                    groupOrder.beVisible()
                    if (summa < 50){
                        dostavka.beVisible()
                        dostavka.text = "Доставка: 5 BYN"
                        dostavka_pay = true
                    }
                }
                R.id.button_post -> {
                    cleanDataTable(group)
                    groupEuropost.beVisible()
                    if (summa < 50){
                        order.isClickable = false
                        order.setBackgroundResource(R.drawable.basket_button_delete)
                        order.text = "Отправка почтой от 50 BYN"
                    }
                    dostavka.beVisible()
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
                val d = Date(getYear-1900, getMonth, getDay)
                val df = SimpleDateFormat("dd MMMM yyyy")
                date.text = df.format(d)
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
            if (name.text!!.isEmpty() || phone.text!!.isEmpty() || date.text.isEmpty()) {
                showToast("Не все данные введены")
            } else {
                val sb = StringBuffer()

                for (i in viewModel.getBasket()) {
                    sb.append(
                        "Артикул: ${i.articul}\n" +
                                "Название: ${i.name}\n" +
                                "Количество: ${i.amount}\n" +
                                "Стоимость: ${i.cost.toInt() * i.amount}\n" +
                                "Image: ${i.photos[0]}\n\n"
                    )
                }

                sb.append("\nЦена: ${viewModel.getSumOfBasket()} BYN")

                if (dostavka_pay) {
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
            order.setBackgroundResource(R.drawable.button_basket_style)
            order.text = "Заказать"
            order.isClickable = true
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

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerTop.beVisible()
    }
}