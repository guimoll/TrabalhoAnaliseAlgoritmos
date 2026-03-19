package stockexchange.order

import io.mockk.mockk
import stockexchange.investor.Investor
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals

class OrderTest {

    private val investor = mockk<Investor>()

    @Test
    fun testOrderHasCorrectInvestor() {
        val order = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        assertEquals(investor, order.investor)
    }

    @Test
    fun testOrderHasCorrectType() {
        val order = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        assertEquals(OrderType.BUY, order.type)
    }

    @Test
    fun testOrderHasCorrectPrice() {
        val order = Order(investor = investor, type = OrderType.SELL, price = BigDecimal("30.00"))
        assertEquals(BigDecimal("30.00"), order.price)
    }
}