package stockexchange.order

import stockexchange.investor.Investor
import stockexchange.stock.Stock
import stockexchange.trigger.ConditionalOrder
import stockexchange.trigger.PriceTriggerListener
import java.math.BigDecimal

class OrderBook(
    private val stock: Stock
) {
    private val priceTriggerObserver: PriceTriggerListener by lazy {
        PriceTriggerListener(stock = stock, orderBook = this).also(stock::attach)
    }

    val buyOrders: List<Order>
        get() = stock.currentOrders.filter { it.type == OrderType.BUY }

    val sellOrders: List<Order>
        get() = stock.currentOrders.filter { it.type == OrderType.SELL }

    fun placeBuyOrder(investor: Investor, price: BigDecimal) {
        addOrder(Order(investor = investor, type = OrderType.BUY, price = price))
    }

    fun placeSellOrder(investor: Investor, price: BigDecimal) {
        addOrder(Order(investor = investor, type = OrderType.SELL, price = price))
    }

    fun scheduleConditionalOrder(conditionalOrder: ConditionalOrder) {
        priceTriggerObserver.scheduleOrder(conditionalOrder)
    }

    fun addOrder(order: Order) {
        stock.addOrder(order)
    }

    fun removeOrder(order: Order) {
        stock.removeOrder(order)
    }
}