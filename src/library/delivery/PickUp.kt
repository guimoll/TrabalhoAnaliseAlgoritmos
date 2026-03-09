package library.delivery

import java.math.BigDecimal

object PickUp : DeliveryType {

    private val PRICE = BigDecimal.ZERO

    override fun calculateDelivery(weight: Int): BigDecimal = PRICE
}

