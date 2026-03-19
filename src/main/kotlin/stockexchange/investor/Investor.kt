package stockexchange.investor

import stockexchange.stock.Stock
import stockexchange.stock.StockObserver

data class Investor(
    val name: String
) : StockObserver {
    init {
        require(name.isNotBlank()) { "Name must not be blank" }
    }

    override fun update(stock: Stock) {
        println("$name received an update: ${stock.symbol} is now ${stock.currentPrice}")
    }
}