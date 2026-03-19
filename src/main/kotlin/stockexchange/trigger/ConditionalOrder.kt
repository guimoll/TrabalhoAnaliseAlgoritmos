package stockexchange.trigger

import stockexchange.investor.Investor
import stockexchange.order.Order
import stockexchange.order.OrderType
import java.math.BigDecimal

data class ConditionalOrder(
    val investor: Investor,
    val orderType: OrderType,
    val orderPrice: BigDecimal,
    val triggerPrice: BigDecimal,
    val triggerType: PriceTriggerType
) {
    fun shouldTrigger(currentPrice: BigDecimal): Boolean {
        return when (triggerType) {
            PriceTriggerType.GREATER_OR_EQUAL -> currentPrice >= triggerPrice
            PriceTriggerType.LESS_OR_EQUAL -> currentPrice <= triggerPrice
        }
    }

    fun createOrder(): Order {
        return Order(
            investor = investor,
            type = orderType,
            price = orderPrice
        )
    }
}