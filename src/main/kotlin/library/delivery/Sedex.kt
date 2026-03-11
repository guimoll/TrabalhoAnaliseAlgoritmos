package library.delivery

import java.math.BigDecimal

object Sedex : DeliveryType {

    private const val TIER_1_MAX_WEIGHT = 500
    private const val TIER_2_MAX_WEIGHT = 1000
    private const val EXTRA_WEIGHT = 100

    private val TIER_1_PRICE = BigDecimal("12.50")
    private val TIER_2_PRICE = BigDecimal("20.00")
    private val TIER_3_BASE_PRICE = BigDecimal("46.50")
    private val TIER_3_EXTRA_PRICE = BigDecimal("1.50")

    override fun calculateDeliveryPrice(weight: Int): BigDecimal {
        return when {
            weight <= TIER_1_MAX_WEIGHT -> TIER_1_PRICE
            weight <= TIER_2_MAX_WEIGHT -> TIER_2_PRICE
            else -> calculateTier3DeliveryPrice(weight)
        }
    }

    private fun calculateTier3DeliveryPrice(weight: Int): BigDecimal {
        val excessWeight = weight - TIER_2_MAX_WEIGHT
        val excessWeightBlock = (excessWeight + EXTRA_WEIGHT - 1) / EXTRA_WEIGHT

        return TIER_3_BASE_PRICE + (TIER_3_EXTRA_PRICE * excessWeightBlock.toBigDecimal())
    }
}