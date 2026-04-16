package stockexchange.order

import io.mockk.mockk
import stockexchange.investor.Investor
import stockexchange.trigger.PriceTriggerType
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class ConditionalOrderTest {

    private val investor = mockk<Investor>()

    @Test
    fun testShouldTriggerWhenPriceEqualsLessOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.BUY,
            price = BigDecimal("48.00"),
            triggerPrice = BigDecimal("50.00"),
            triggerType = PriceTriggerType.LESS_OR_EQUAL
        )
        assertTrue(conditionalOrder.shouldTrigger(BigDecimal("50.00")))
    }

    @Test
    fun testShouldTriggerWhenPriceBelowLessOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.BUY,
            price = BigDecimal("48.00"),
            triggerPrice = BigDecimal("50.00"),
            triggerType = PriceTriggerType.LESS_OR_EQUAL
        )
        assertTrue(conditionalOrder.shouldTrigger(BigDecimal("45.00")))
    }

    @Test
    fun testShouldNotTriggerWhenPriceAboveLessOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.BUY,
            price = BigDecimal("48.00"),
            triggerPrice = BigDecimal("50.00"),
            triggerType = PriceTriggerType.LESS_OR_EQUAL
        )
        assertFalse(conditionalOrder.shouldTrigger(BigDecimal("51.00")))
    }

    @Test
    fun testShouldTriggerWhenPriceEqualsGreaterOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.SELL,
            price = BigDecimal("55.00"),
            triggerPrice = BigDecimal("55.00"),
            triggerType = PriceTriggerType.GREATER_OR_EQUAL
        )
        assertTrue(conditionalOrder.shouldTrigger(BigDecimal("55.00")))
    }

    @Test
    fun testShouldTriggerWhenPriceAboveGreaterOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.SELL,
            price = BigDecimal("55.00"),
            triggerPrice = BigDecimal("55.00"),
            triggerType = PriceTriggerType.GREATER_OR_EQUAL
        )
        assertTrue(conditionalOrder.shouldTrigger(BigDecimal("60.00")))
    }

    @Test
    fun testShouldNotTriggerWhenPriceBelowGreaterOrEqualThreshold() {
        val conditionalOrder = ConditionalOrder(
            investor = investor,
            type = OrderType.SELL,
            price = BigDecimal("55.00"),
            triggerPrice = BigDecimal("55.00"),
            triggerType = PriceTriggerType.GREATER_OR_EQUAL
        )
        assertFalse(conditionalOrder.shouldTrigger(BigDecimal("54.00")))
    }

    @Test
    fun testCreateOrderConvertsConditionalOrderToImmediateOrder() {
        val testInvestor = mockk<Investor>()
        val orderPrice = BigDecimal("48.50")
        val triggerPrice = BigDecimal("50.00")
        
        val conditionalOrder = ConditionalOrder(
            investor = testInvestor,
            type = OrderType.SELL,
            price = orderPrice,
            triggerPrice = triggerPrice,
            triggerType = PriceTriggerType.LESS_OR_EQUAL
        )
        
        val createdOrder = conditionalOrder.createOrder()
        
        assertEquals(testInvestor, createdOrder.investor)
        assertEquals(OrderType.SELL, createdOrder.type)
        assertEquals(orderPrice, createdOrder.price)
        
        assertTrue(createdOrder != conditionalOrder)
    }
}