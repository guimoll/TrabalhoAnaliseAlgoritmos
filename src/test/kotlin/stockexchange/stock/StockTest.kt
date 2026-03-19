package stockexchange.stock

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import stockexchange.investor.Investor
import stockexchange.notification.NotificationService
import stockexchange.order.Order
import java.math.BigDecimal
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StockTest {

    @BeforeTest
    fun setup() {
        mockkObject(NotificationService)
        every { NotificationService.subscribed(any(), any(), any()) } returns Unit
        every { NotificationService.unsubscribed(any(), any()) } returns Unit
        every { NotificationService.priceUpdated(any(), any(), any()) } returns Unit
    }

    @Test
    fun testCreateStockWithValidSymbolAndPrice() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        assertEquals("MCD", stock.symbol)
        assertEquals(BigDecimal("50.00"), stock.currentPrice)
    }

    @Test
    fun testCreateStockWithBlankSymbolThrows() {
        assertFailsWith<IllegalArgumentException> {
            Stock(symbol = "", initialPrice = BigDecimal("50.00"))
        }
    }

    @Test
    fun testCreateStockWithZeroPriceThrows() {
        assertFailsWith<IllegalArgumentException> {
            Stock(symbol = "MCD", initialPrice = BigDecimal.ZERO)
        }
    }

    @Test
    fun testCreateStockWithNegativePriceThrows() {
        assertFailsWith<IllegalArgumentException> {
            Stock(symbol = "MCD", initialPrice = BigDecimal("-1.00"))
        }
    }

    @Test
    fun testUpdatePriceChangesCurrentPrice() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        stock.updatePrice(BigDecimal("55.00"))
        assertEquals(BigDecimal("55.00"), stock.currentPrice)
    }

    @Test
    fun testUpdatePriceWithZeroThrows() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        assertFailsWith<IllegalArgumentException> {
            stock.updatePrice(BigDecimal.ZERO)
        }
    }

    @Test
    fun testUpdatePriceNotifiesObservers() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val observer = mockk<StockObserver>(relaxed = true)
        stock.attach(observer)

        stock.updatePrice(BigDecimal("55.00"))

        verify { observer.update(stock) }
    }

    @Test
    fun testAddOrderAddsToCurrentOrders() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val order = mockk<Order>()
        stock.addOrder(order)
        assertTrue(stock.currentOrders.contains(order))
    }

    @Test
    fun testRemoveOrderRemovesFromCurrentOrders() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val order = mockk<Order>()
        stock.addOrder(order)
        stock.removeOrder(order)
        assertFalse(stock.currentOrders.contains(order))
    }

    @Test
    fun testAttachInvestorNotifiesSubscribed() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val investor = mockk<Investor>()
        every { investor.name } returns "Mariana"

        stock.attach(investor)

        verify { NotificationService.subscribed("Mariana", "MCD", BigDecimal("50.00")) }
    }

    @Test
    fun testAttachNonInvestorObserverDoesNotNotifySubscribed() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val observer = mockk<StockObserver>(relaxed = true)

        stock.attach(observer)

        verify(exactly = 0) { NotificationService.subscribed(any(), any(), any()) }
    }

    @Test
    fun testDetachInvestorNotifiesUnsubscribed() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val investor = mockk<Investor>()
        every { investor.name } returns "Mariana"

        stock.attach(investor)
        stock.detach(investor)

        verify { NotificationService.unsubscribed("Mariana", "MCD") }
    }

    @Test
    fun testDetachObserverStopsReceivingUpdates() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val observer = mockk<StockObserver>(relaxed = true)

        stock.attach(observer)
        stock.detach(observer)
        stock.updatePrice(BigDecimal("55.00"))

        verify(exactly = 0) { observer.update(any()) }
    }

    @Test
    fun testCurrentOrdersReturnsImmutableCopy() {
        val stock = Stock(symbol = "MCD", initialPrice = BigDecimal("50.00"))
        val order = mockk<Order>()
        stock.addOrder(order)

        val orders = stock.currentOrders
        assertEquals(1, orders.size)
    }
}