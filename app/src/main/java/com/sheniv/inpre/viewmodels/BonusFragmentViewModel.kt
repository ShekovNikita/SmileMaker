package com.sheniv.inpre.viewmodels

import androidx.lifecycle.ViewModel

class BonusFragmentViewModel : ViewModel() {

    private val text_bonus = """Отличные новости для любимых покупателей!

Оформите заказ за 3 дня и получите скидку за предзаказ в размере 10%. Если Вы не из города Гомель и Вам нужна доставка европочтой — заказ необходимо оформить минимум за 7 дней.

На композиции, которые есть в наличии, скидка 10% действует автоматически.

Совершайте покупки и становитесь участниками БОНУСНОЙ ПРОГРАММЫ.

Участники данной программы получают наклейку за каждые 50 р ЕДИНОВРЕМЕННОГО заказа. Собрав 7 наклеек, Вы получаете возможность выбрать композицию стоимостью до 50 р в подарок (за 1 копейку). Также можно выбрать композицию с большей стоимостью и доплатить разницу. После выполнения условий бонусной программы, композицию можно заказать на любую выбранную дату.

Рассчитывайтесь через мобильное приложение ОПЛАТИ на нашем сайте и получайте дополнительную скидку 6% на любые товары! А также возвращайте 3% от каждой покупки, произведенной через приложение (с условиями рекламной программы лояльности «Манибэк» можно ознакомиться в мобильном приложении ОПЛАТИ).

Приятных покупок!"""

    fun getBonusText() = text_bonus
}