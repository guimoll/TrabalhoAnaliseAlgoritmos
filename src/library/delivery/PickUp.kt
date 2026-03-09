package library.delivery

import java.math.BigDecimal

object PickUp : DeliveryType {

    private val PRICE = BigDecimal.ZERO

    override fun calculateDeliveryPrice(weight: Int): BigDecimal = PRICE
}

