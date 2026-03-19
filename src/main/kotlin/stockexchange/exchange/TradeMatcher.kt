package stockexchange.exchange

import stockexchange.order.Order
import stockexchange.order.OrderBook

class TradeMatcher {

    fun findMatch(orderBook: OrderBook): Pair<Order, Order>? {
        val sellOrders = orderBook.sellOrders
        return orderBook.buyOrders.firstNotNullOfOrNull { buyOrder ->
            sellOrders.find { it.price == buyOrder.price }?.let { buyOrder to it }
        }
    }
}