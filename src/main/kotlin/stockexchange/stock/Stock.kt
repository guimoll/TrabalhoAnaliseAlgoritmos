package stockexchange.stock

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
    }

    override fun detach(observer: StockObserver) {
        observers.remove(observer)
    }

    private fun notifyAllObservers() {
        observers.forEach { it.update(this) }
    }
}