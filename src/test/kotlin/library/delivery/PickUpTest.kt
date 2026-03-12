package library.delivery

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PickUpTest {

    @Test
    fun testCalculateDeliveryPriceForMinWeight() {
        val price = PickUp.calculateDeliveryPrice(1)
        assertEquals(BigDecimal.ZERO, price)
    }

    @Test
    fun testCalculateDeliveryPriceForMediumWeight() {
        val price = PickUp.calculateDeliveryPrice(1000)
        assertEquals(BigDecimal.ZERO, price)
    }

    @Test
    fun testCalculateDeliveryPriceForHighWeight() {
        val price = PickUp.calculateDeliveryPrice(5000)
        assertEquals(BigDecimal.ZERO, price)
    }
}
