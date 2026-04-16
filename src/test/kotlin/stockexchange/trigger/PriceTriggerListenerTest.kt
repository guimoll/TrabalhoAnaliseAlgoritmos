package stockexchange.trigger

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.verify
import stockexchange.notification.NotificationService
import stockexchange.order.ConditionalOrder
import stockexchange.order.OrderBook
import stockexchange.stock.Stock
import java.math.BigDecimal
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class PriceTriggerListenerTest {

    private val stock = mockk<Stock>(relaxed = true)
    private val orderBook = mockk<OrderBook>(relaxed = true)
    private val listener = PriceTriggerListener(stock = stock, orderBook = orderBook)

    @BeforeTest
    fun setup() {
        mockkObject(NotificationService)
        every { NotificationService.conditionalTriggered(any(), any()) } returns Unit
        every { NotificationService.orderPlaced(any(), any()) } returns Unit
        every { stock.symbol } returns "MCD"
    }

    @Test
    fun testScheduleOrderAddsConditionalOrder() {
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)
        every { conditionalOrder.shouldTrigger(any()) } returns false

        listener.scheduleOrder(conditionalOrder)
        listener.update(stock)

        verify(exactly = 0) { orderBook.addOrder(any()) }
    }

    @Test
    fun testUpdateTriggersConditionalOrderWhenConditionMet() {
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)
        every { conditionalOrder.shouldTrigger(any()) } returns true
        every { stock.currentPrice } returns BigDecimal("48.00")

        listener.scheduleOrder(conditionalOrder)
        listener.update(stock)

        verify { orderBook.addOrder(any()) }
    }

    @Test
    fun testUpdateNotifiesWhenConditionalOrderTriggered() {
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)
        every { conditionalOrder.shouldTrigger(any()) } returns true
        every { stock.currentPrice } returns BigDecimal("48.00")

        listener.scheduleOrder(conditionalOrder)
        listener.update(stock)

        verify { NotificationService.conditionalTriggered(conditionalOrder, "MCD") }
    }

    @Test
    fun testUpdateRemovesConditionalOrderAfterTrigger() {
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)
        every { conditionalOrder.shouldTrigger(any()) } returns true
        every { stock.currentPrice } returns BigDecimal("48.00")

        listener.scheduleOrder(conditionalOrder)
        listener.update(stock)
        listener.update(stock)

        verify(exactly = 1) { orderBook.addOrder(any()) }
    }

    @Test
    fun testUpdateDoesNotTriggerWhenConditionNotMet() {
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)
        every { conditionalOrder.shouldTrigger(any()) } returns false
        every { stock.currentPrice } returns BigDecimal("60.00")

        listener.scheduleOrder(conditionalOrder)
        listener.update(stock)

        verify(exactly = 0) { orderBook.addOrder(any()) }
    }

    @Test
    fun testUpdateThrowsWhenCalledWithDifferentStock() {
        val differentStock = mockk<Stock>()
        val conditionalOrder = mockk<ConditionalOrder>(relaxed = true)

        listener.scheduleOrder(conditionalOrder)

        assertFailsWith<IllegalArgumentException> {
            listener.update(differentStock)
        }
    }
}