package stockexchange.investor

import stockexchange.notification.NotificationService
import stockexchange.stock.Stock
import stockexchange.stock.StockObserver
import java.util.UUID

data class Investor(
    val name: String,
    val id: String = UUID.randomUUID().toString()
) : StockObserver {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    override fun update(stock: Stock) {
        NotificationService.priceUpdated(name, stock.symbol, stock.currentPrice)
    }
}