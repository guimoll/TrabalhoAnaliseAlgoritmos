package library.delivery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class SedexTest {

    @Test
    fun testCalculateDeliveryPriceForTier1MinWeight() {
        val price = Sedex.calculateDeliveryPrice(1)
        assertEquals(BigDecimal("12.50"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier1MaxWeight() {
        val price = Sedex.calculateDeliveryPrice(500)
        assertEquals(BigDecimal("12.50"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier2MinWeight() {
        val price = Sedex.calculateDeliveryPrice(501)
        assertEquals(BigDecimal("20.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier2MaxWeight() {
        val price = Sedex.calculateDeliveryPrice(1000)
        assertEquals(BigDecimal("20.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier3BaseWeight() {
        val price = Sedex.calculateDeliveryPrice(1001)
        assertEquals(BigDecimal("48.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier3OneFullBlock() {
        val price = Sedex.calculateDeliveryPrice(1100)
        assertEquals(BigDecimal("48.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier3TwoBlocks() {
        val price = Sedex.calculateDeliveryPrice(1101)
        assertEquals(BigDecimal("49.50"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier3TwoFullBlocks() {
        val price = Sedex.calculateDeliveryPrice(1200)
        assertEquals(BigDecimal("49.50"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier3ThreeBlocks() {
        val price = Sedex.calculateDeliveryPrice(1201)
        assertEquals(BigDecimal("51.00"), price)
    }
}
