package stockexchange

import stockexchange.order.Order
import stockexchange.order.OrderType

class OrderBook {

    private val orders = mutableListOf<Order>()

    val buyOrders: List<Order>
        get() = orders.filter { it.type == OrderType.BUY }

    val sellOrders: List<Order>
        get() = orders.filter { it.type == OrderType.SELL }

    val allOrders: List<Order>
        get() = orders.toList()

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun removeOrder(order: Order) {
        orders.remove(order)
    }
}