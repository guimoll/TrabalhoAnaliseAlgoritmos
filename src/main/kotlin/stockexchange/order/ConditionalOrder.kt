package stockexchange.order

import stockexchange.investor.Investor
import stockexchange.trigger.PriceTriggerType
import java.math.BigDecimal

data class ConditionalOrder(
    override val investor: Investor,
    override val type: OrderType,
    override val price: BigDecimal,
    val triggerPrice: BigDecimal,
    val triggerType: PriceTriggerType
) : OrderRequest {

    fun shouldTrigger(currentPrice: BigDecimal): Boolean {
        return when (triggerType) {
            PriceTriggerType.GREATER_OR_EQUAL -> currentPrice >= triggerPrice
            PriceTriggerType.LESS_OR_EQUAL -> currentPrice <= triggerPrice
        }
    }

    fun createOrder(): Order {
        return Order(
            investor = investor,
            type = type,
            price = price
        )
    }
}