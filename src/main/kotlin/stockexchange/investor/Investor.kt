package stockexchange.investor

import stockexchange.notification.NotificationService
import stockexchange.stock.Stock
import stockexchange.stock.StockObserver

data class Investor(
    val name: String
) : StockObserver {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    override fun update(stock: Stock) {
        NotificationService.priceUpdated(name, stock.symbol, stock.currentPrice)
    }
}