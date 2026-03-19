package stockexchange.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import stockexchange.investor.Investor
import stockexchange.notification.NotificationService
import stockexchange.stock.Stock
import java.math.BigDecimal
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class OrderBookTest {

    private val investor = mockk<Investor>()
    private val investor2 = mockk<Investor>()
    private val stock = mockk<Stock>(relaxed = true)
    private val orderBook = OrderBook(stock)

    @BeforeTest
    fun setup() {
        mockkObject(NotificationService)
        every { NotificationService.orderPlaced(any(), any()) } returns Unit
        every { NotificationService.orderScheduled(any(), any()) } returns Unit
        every { stock.symbol } returns "MCD"
    }

    @Test
    fun testPlaceBuyOrderAddsToStock() {
        orderBook.placeBuyOrder(investor, BigDecimal("50.00"))
        verify { stock.addOrder(match { it.type == OrderType.BUY && it.price == BigDecimal("50.00") }) }
    }

    @Test
    fun testPlaceSellOrderAddsToStock() {
        orderBook.placeSellOrder(investor, BigDecimal("50.00"))
        verify { stock.addOrder(match { it.type == OrderType.SELL && it.price == BigDecimal("50.00") }) }
    }

    @Test
    fun testBuyOrdersReturnsOnlyBuyOrders() {
        val buyOrder = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        val sellOrder = Order(investor = investor2, type = OrderType.SELL, price = BigDecimal("50.00"))
        every { stock.currentOrders } returns listOf(buyOrder, sellOrder)

        assertTrue(orderBook.buyOrders.all { it.type == OrderType.BUY })
    }

    @Test
    fun testSellOrdersReturnsOnlySellOrders() {
        val buyOrder = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        val sellOrder = Order(investor = investor2, type = OrderType.SELL, price = BigDecimal("50.00"))
        every { stock.currentOrders } returns listOf(buyOrder, sellOrder)

        assertTrue(orderBook.sellOrders.all { it.type == OrderType.SELL })
    }

    @Test
    fun testAddOrderNotifiesOrderPlaced() {
        val order = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        orderBook.addOrder(order)
        verify { NotificationService.orderPlaced(order, "MCD") }
    }

    @Test
    fun testAddOrderDelegatesToStock() {
        val order = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        orderBook.addOrder(order)
        verify { stock.addOrder(order) }
    }

    @Test
    fun testRemoveOrderDelegatesToStock() {
        val order = Order(investor = investor, type = OrderType.BUY, price = BigDecimal("50.00"))
        orderBook.removeOrder(order)
        verify { stock.removeOrder(order) }
    }

    @Test
    fun testScheduleConditionalOrderNotifiesOrderScheduled() {
        val conditionalOrder = mockk<ConditionalOrder>()
        orderBook.scheduleConditionalOrder(conditionalOrder)
        verify { NotificationService.orderScheduled(conditionalOrder, "MCD") }
    }

    @Test
    fun testPlaceBuyOrderThrowsExceptionWhenPriceIsZero() {
        val exception = assertFailsWith<IllegalArgumentException> {
            orderBook.placeBuyOrder(investor, BigDecimal.ZERO)
        }
        assertTrue(exception.message?.contains("Order price must be greater than 0") == true)
    }

    @Test
    fun testPlaceBuyOrderThrowsExceptionWhenPriceIsNegative() {
        val exception = assertFailsWith<IllegalArgumentException> {
            orderBook.placeBuyOrder(investor, BigDecimal("-1.00"))
        }
        assertTrue(exception.message?.contains("Order price must be greater than 0") == true)
    }

    @Test
    fun testPlaceSellOrderThrowsExceptionWhenPriceIsZero() {
        val exception = assertFailsWith<IllegalArgumentException> {
            orderBook.placeSellOrder(investor, BigDecimal.ZERO)
        }
        assertTrue(exception.message?.contains("Order price must be greater than 0") == true)
    }

    @Test
    fun testPlaceSellOrderThrowsExceptionWhenPriceIsNegative() {
        val exception = assertFailsWith<IllegalArgumentException> {
            orderBook.placeSellOrder(investor, BigDecimal("-1.00"))
        }
        assertTrue(exception.message?.contains("Order price must be greater than 0") == true)
    }
}
