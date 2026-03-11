package library.delivery

import java.math.BigDecimal

object PickUp : DeliveryType {

    private val ZERO = BigDecimal.ZERO

    override fun calculateDeliveryPrice(weight: Int): BigDecimal = ZERO
}

