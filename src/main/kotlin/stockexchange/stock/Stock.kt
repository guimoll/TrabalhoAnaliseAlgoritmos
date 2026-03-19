package stockexchange.stock

import stockexchange.notification.NotificationService
import stockexchange.order.Order
import java.math.BigDecimal

class Stock(
    val symbol: String,
    initialPrice: BigDecimal
) : StockSubject {
    init {
        require(symbol.isNotBlank()) { "Symbol must not be blank" }
        require(initialPrice > BigDecimal.ZERO) { "Initial price must be greater than 0" }
    }

    private val observers = mutableSetOf<StockObserver>()
    private val orders = mutableListOf<Order>()

    var currentPrice: BigDecimal = initialPrice
        private set

    val currentOrders: List<Order>
        get() = orders.toList()

    fun updatePrice(newPrice: BigDecimal) {
        require(newPrice > BigDecimal.ZERO) { "New price must be greater than 0" }
        currentPrice = newPrice
        notifyAllObservers()
    }

    fun addOrder(order: Order) {
        orders.add(order)
    }

    fun removeOrder(order: Order) {
        orders.remove(order)
    }

    override fun attach(observer: StockObserver) {
        observers.add(observer)
        if (observer is stockexchange.investor.Investor) {
            NotificationService.subscribed(observer.name, symbol, currentPrice)
        }
    }

    override fun detach(observer: StockObserver) {
        observers.remove(observer)
        if (observer is stockexchange.investor.Investor) {
            NotificationService.unsubscribed(observer.name, symbol)
        }
    }

    private fun notifyAllObservers() {
        observers.forEach { it.update(this) }
    }
}