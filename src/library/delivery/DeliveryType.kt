package library.delivery

import java.math.BigDecimal

sealed interface DeliveryType {
    fun calculateDelivery(weight: Int): BigDecimal
}
