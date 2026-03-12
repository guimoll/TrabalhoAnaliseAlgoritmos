package library.delivery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PacTest {

    @Test
    fun testCalculateDeliveryPriceForTier1MinWeight() {
        val price = Pac.calculateDeliveryPrice(1)
        assertEquals(BigDecimal("10.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier1MaxWeight() {
        val price = Pac.calculateDeliveryPrice(1000)
        assertEquals(BigDecimal("10.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier2MinWeight() {
        val price = Pac.calculateDeliveryPrice(1001)
        assertEquals(BigDecimal("15.00"), price)
    }

    @Test
    fun testCalculateDeliveryPriceForTier2MaxWeight() {
        val price = Pac.calculateDeliveryPrice(2000)
        assertEquals(BigDecimal("15.00"), price)
    }

    @Test
    fun testThrowExceptionWhenWeightExceedsMaximum() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            Pac.calculateDeliveryPrice(2001)
        }
        assertEquals("PAC does not accept packages above 2kg", exception.message)
    }
}
