package stockexchange.notification

import stockexchange.order.Order
import stockexchange.order.ConditionalOrder
import java.math.BigDecimal

object NotificationService {

    fun orderPlaced(order: Order, symbol: String) {
        println("[ORDER PLACED] ${order.investor.name} placed a ${order.type} order for $symbol at R$${order.price}")
    }

    fun orderScheduled(conditionalOrder: ConditionalOrder, symbol: String) {
        println(
            "[ORDER SCHEDULED] ${conditionalOrder.investor.name} scheduled a ${conditionalOrder.type} " +
                    "order for $symbol at R$${conditionalOrder.price} " +
                    "when price ${conditionalOrder.triggerType} R$${conditionalOrder.triggerPrice}"
        )
    }

    fun conditionalTriggered(conditionalOrder: ConditionalOrder, symbol: String) {
        println(
            "[CONDITIONAL TRIGGERED] ${conditionalOrder.investor.name}'s conditional order was activated: " +
                    "${conditionalOrder.type} $symbol at R$${conditionalOrder.price} " +
                    "(trigger: price ${conditionalOrder.triggerType} R$${conditionalOrder.triggerPrice})"
        )
    }

    fun subscribed(investorName: String, symbol: String, currentPrice: BigDecimal) {
        println("[SUBSCRIBED] $investorName is now watching $symbol (current price: R$$currentPrice)")
    }

    fun unsubscribed(investorName: String, symbol: String) {
        println("[UNSUBSCRIBED] $investorName stopped watching $symbol")
    }

    fun priceUpdated(investorName: String, symbol: String, newPrice: BigDecimal) {
        println("[PRICE UPDATE] $investorName received an update: $symbol is now R$$newPrice")
    }

    fun matchExecuted(buyOrder: Order, sellOrder: Order, symbol: String, price: BigDecimal) {
        println(
            "[MATCH EXECUTED] $symbol traded at R$$price — " +
                    "buyer: ${buyOrder.investor.name}, seller: ${sellOrder.investor.name}"
        )
    }
}