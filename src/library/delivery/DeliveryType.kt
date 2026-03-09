package library.delivery

import java.math.BigDecimal

sealed interface DeliveryType {
    fun calculateDeliveryPrice(weight: Int): BigDecimal
}
