package stockexchange.trigger

import stockexchange.notification.NotificationService
import stockexchange.order.ConditionalOrder
import stockexchange.order.OrderBook
import stockexchange.stock.Stock
import stockexchange.stock.StockObserver

class PriceTriggerListener(
    private val stock: Stock,
    private val orderBook: OrderBook
) : StockObserver {

    private val scheduledOrders = mutableListOf<ConditionalOrder>()

    fun scheduleOrder(conditionalOrder: ConditionalOrder) {
        scheduledOrders.add(conditionalOrder)
    }

    override fun update(stock: Stock) {
        require(stock === this.stock) { "StockPriceTriggerObserver received update from an unexpected stock" }

        val iterator = scheduledOrders.iterator()
        while (iterator.hasNext()) {
            val conditionalOrder = iterator.next()
            if (conditionalOrder.shouldTrigger(stock.currentPrice)) {
                NotificationService.conditionalTriggered(conditionalOrder, stock.symbol)
                orderBook.addOrder(conditionalOrder.createOrder())
                iterator.remove()
            }
        }
    }
}