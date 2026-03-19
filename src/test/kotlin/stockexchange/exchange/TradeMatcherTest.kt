package stockexchange.exchange

import io.mockk.every
import io.mockk.mockk
import stockexchange.order.Order
import stockexchange.order.OrderBook
import stockexchange.order.OrderType
import stockexchange.investor.Investor
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class TradeMatcherTest {

    private val investor = mockk<Investor>()
    private val orderBook = mockk<OrderBook>()

    @Test
    fun testFindMatchReturnsMatchWhenPricesAreEqual() {
        val buyOrder = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        val sellOrder = Order(investor = investor, type = OrderType.SELL, price = BigDecimal("50.00"))

        every { orderBook.buyOrders } returns listOf(buyOrder)
        every { orderBook.sellOrders } returns listOf(sellOrder)

        val result = TradeMatcher.findMatch(orderBook)

        assertEquals(buyOrder to sellOrder, result)
    }

    @Test
    fun testFindMatchReturnsNullWhenNoBuyOrders() {
        every { orderBook.buyOrders } returns emptyList()
        every { orderBook.sellOrders } returns listOf(
            Order(investor = investor, type = OrderType.SELL, price = BigDecimal("50.00"))
        )

        assertNull(TradeMatcher.findMatch(orderBook))
    }

    @Test
    fun testFindMatchReturnsNullWhenNoSellOrders() {
        every { orderBook.buyOrders } returns listOf(
            Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        )
        every { orderBook.sellOrders } returns emptyList()

        assertNull(TradeMatcher.findMatch(orderBook))
    }

    @Test
    fun testFindMatchReturnsNullWhenPricesDontMatch() {
        every { orderBook.buyOrders } returns listOf(
            Order(investor = investor, type = OrderType.BUY, price = BigDecimal("49.00"))
        )
        every { orderBook.sellOrders } returns listOf(
            Order(investor = investor, type = OrderType.SELL, price = BigDecimal("50.00"))
        )

        assertNull(TradeMatcher.findMatch(orderBook))
    }

    @Test
    fun testFindMatchReturnsFirstMatchingPair() {
        val investor2 = mockk<Investor>()
        val buyOrder1 = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        val buyOrder2 = Order(investor = investor2, type = OrderType.BUY, price = BigDecimal("51.00"))
        val sellOrder = Order(investor = investor, type = OrderType.SELL, price = BigDecimal("50.00"))

        every { orderBook.buyOrders } returns listOf(buyOrder1, buyOrder2)
        every { orderBook.sellOrders } returns listOf(sellOrder)

        val result = TradeMatcher.findMatch(orderBook)

        assertEquals(buyOrder1 to sellOrder, result)
    }
}