package library.delivery

import java.math.BigDecimal

object Pac : DeliveryType {

    private const val TIER_1_MAX_WEIGHT = 1000
    private const val TIER_2_MAX_WEIGHT = 2000

    private val TIER_1_PRICE = BigDecimal("10.00")
    private val TIER_2_PRICE = BigDecimal("15.00")

    override fun calculateDelivery(weight: Int): BigDecimal = when {
        weight <= TIER_1_MAX_WEIGHT -> TIER_1_PRICE
        weight <= TIER_2_MAX_WEIGHT -> TIER_2_PRICE
        else -> throw IllegalArgumentException(
            "PAC does not accept packages above 2kg"
        )
    }
}
